import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import okhttp3.*;
import okhttp3.internal.http2.Header;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Set;

public class appdownloadDC {
    public static final String userName = "shubhamr";
    public static final String accessKey = "M1hTTfelp95y0WKq0MSKORBzWD7xpFGOTv5KlMTZ18qnAcGjId";
    public static final String URL = "https://" + userName + ":" + accessKey + "@beta-hub.lambdatest.com/wd/hub";
    public static  FileOutputStream fos = null;
    public static  File file=null;








    public static void main(String[] args) throws Exception {

        OkHttpClient client_1 = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType_1 = MediaType.parse("text/plain");
        RequestBody body_1 = RequestBody.create(mediaType_1, "");
        Request request_1 = new Request.Builder()
                .url("https://content.dropboxapi.com/2/sharing/get_shared_link_file")
                .method("POST", body_1)
                .addHeader("Authorization", "Bearer sl.BB3piqQwNqC21hVIGM5N9FkhIeSyizyZN4xn1BhZlUElHm0rS-6O_Pw3gD98tdYJHa2Z96QAUEWC0maFXU2B6-x2rWNQFU48NxJ-iN9Dhm_0obUU9vaKKf4LP0dNrr19GUtprM1U")
                .addHeader("Dropbox-API-Arg", "{\"url\": \"https://www.dropbox.com/s/tl81nkq8o50liq9/WikipediaSample.apk?dl=1\"}")
                .build();
        Response response_1 = client_1.newCall(request_1).execute();
        System.out.println("--------"+response_1);

        String responseBody= response_1.headers().toString();
       // System.out.println(responseBody);
        file = new File("D://jar//WikipediaSample.apk");
        fos = new FileOutputStream(file);
        byte[] bytesArray = response_1.body().bytes();

        fos.write(bytesArray);
        fos.flush();
        fos.close();
        System.out.println("File Written Successfully");
        Thread.sleep(5000);





        String encoding = Base64.getEncoder().encodeToString((userName + ":" + accessKey).getBytes("UTF-8"));
        System.out.println("encoding:------"+encoding);


        OkHttpClient client = new OkHttpClient().newBuilder() .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("name","\"WikipediaSample\"").addFormDataPart("appFile","/D:/jar/WikipediaSample.apk",
                RequestBody.create(MediaType.parse("application/octet-stream"),
                        new File("/D:/jar/WikipediaSample.apk"))) .build();
        Request request = new Request.Builder() .url("https://manual-api.lambdatest.com/app/upload/realDevice").method("POST", body).addHeader("Authorization", "Basic "+encoding).build();
        Response response = client.newCall(request).execute();
        String Responsebody= response.body().string();
        System.out.println("response===="+response);
        System.out.print("ResponseBody:------"+Responsebody);
        JSONObject obj = new JSONObject(Responsebody);
        System.out.println("App URL:"+obj.get("app_url"));
        String app_url=obj.get("app_url").toString();
        System.out.println("App Uploaded");



        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformVersion", "10");
        caps.setCapability("deviceName", "Galaxy Tab S4");
        caps.setCapability("isRealMobile", "true");
        caps.setCapability("app", app_url);
        caps.setCapability("platformName", "android");
        caps.setCapability("build", "Java Automation");
        caps.setCapability("name", "app testing");
        caps.setCapability("devicelog",true);
        caps.setCapability("network",true);


        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("https://"+userName+":"+accessKey+"@beta-hub.lambdatest.com/wd/hub"), caps);

        driver.findElement(MobileBy.AccessibilityId("Search Wikipedia")).click();
        driver.findElement(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Cristiano Ronaldo");
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));



        System.out.println("Test Passed");
        driver.quit();


        file.delete();

















       // file.delete();
       // System.out.println(" File is deleted from the source");





       //System.out.println("response---"+response.body().string());
















    }
}
