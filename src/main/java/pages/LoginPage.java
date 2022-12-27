package pages;

import core.ElementHandler;
import models.LoginData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {

    private static final By inputUsername = By.id("user-name");
    private static final By inputPassword = By.id("password");
    private static final By buttonLogin = By.id("login-button");


    public static void login(String username, String password) {
        ElementHandler.openApplication();
        ElementHandler.waitTillPageLoad(buttonLogin);
        ElementHandler.enterText(inputUsername, username);
        ElementHandler.enterText(inputPassword, password);
        ElementHandler.click(buttonLogin);
    }

    public static void login(LoginData loginData) {
        ElementHandler.openApplication();
        ElementHandler.waitTillCondition(ExpectedConditions.elementToBeClickable(buttonLogin), 100);
        ElementHandler.enterText(inputUsername, loginData.getUsername());
        ElementHandler.enterText(inputPassword, loginData.getPassword());
        ElementHandler.click(buttonLogin);
    }
}
