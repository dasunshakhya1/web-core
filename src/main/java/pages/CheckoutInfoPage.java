package pages;

import core.ElementHandler;
import models.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckoutInfoPage {

    private static final By title = By.className("title");
    private static final By inputFirstName = By.id("first-name");
    private static final By inputLastName = By.id("last-name");
    private static final By inputPostalCode = By.id("postal-code");

    private static final By buttonContinue = By.id("continue");

    public static void waitTillPageLoad() {
        ElementHandler.waitTillPageLoad(title);
    }

    public static void enterUserDetails(UserData userData) {
        ElementHandler.enterText(inputFirstName, userData.getFirstName());
        ElementHandler.enterText(inputLastName, userData.getLastName());
        ElementHandler.enterText(inputPostalCode, userData.getPostalCode());
        ElementHandler.click(buttonContinue);
    }
}
