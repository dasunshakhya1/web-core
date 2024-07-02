package core;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import configs.Configs;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DriverManager {


    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = getRequestedDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }


    private static WebDriver getRequestedDriver() {
        WebDriver webDriver;
        String browser = Configs.BROWSER;
        switch (browser) {
            case "FIREFOX":
                webDriver = getFirefoxDriver();
                break;
            case "SAFARI":
                webDriver = getSafariDriver();
                break;
            default:
                webDriver = getChromeDriver();
        }
        return webDriver;
    }


    private static WebDriver getSafariDriver() {
        return new SafariDriver();
    }

    private static WebDriver getFirefoxDriver() {
        return new FirefoxDriver();
    }

    private static WebDriver getChromeDriver() {
        return new ChromeDriver();
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
