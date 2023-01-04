package core;

import enums.SelectOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class ElementHandler {

    private static WebDriver driver;

    public static void openApplication() {
        driver = DriverManager.getDriver();
        driver.get(Configs.BASE_URL);
    }


    public static WebDriver getDriver() {
        return driver;
    }

    public static WebElement findElement(By by) {
        return driver.findElement(by);
    }

    public static List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public static void clearTextField(By by) {
        findElement(by).clear();
    }

    public static void clearTextField(WebElement element) {
        element.clear();
    }

    public static void click(By by) {
        findElement(by).click();
    }

    public static void click(WebElement element) {
        element.click();
    }

    public static void enterText(By by, String text) {
        clearTextField(by);
        findElement(by).sendKeys(text);
    }

    public static void enterText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public static <T> void waitTillCondition(ExpectedCondition<T> condition, int timeout) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(timeout, 100));
        webDriverWait.until(condition);
    }

    public static void waitTillPageLoad(By by) {
        waitTillCondition(ExpectedConditions.elementToBeClickable(by), 200);
    }

    public static String getElementText(By by) {
        return findElement(by).getText();
    }

    public static String getElementText(WebElement element) {
        return element.getText();
    }

    public static void quiteDriver() {
        DriverManager.quitDriver();
    }

    public static void selectOption(By by, SelectOptions selectOption, Object option) {
        Select select = new Select(findElement(by));

        switch (selectOption) {
            case VALUE:
                select.selectByValue((String) option);
                break;
            case INDEX:
                select.selectByIndex((Integer) option);
            default:
                select.selectByVisibleText((String) option);
        }

    }
}
