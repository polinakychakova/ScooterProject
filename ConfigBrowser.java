package utils;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import com.codeborne.selenide.Configuration;

public class ConfigBrowser {
    public void setBrowserName(String browserName){
        Configuration.browser = browserName;
//        Configuration.browserSize= "1920x1080";
        Configuration.startMaximized= true;
        Configuration.baseUrl = "https://qa-scooter.praktikum-services.ru/";
    }
}
