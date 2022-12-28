package pages;

import core.ElementHandler;
import models.ProductData;
import org.openqa.selenium.By;
import pages.views.ProductView;

import java.util.Set;

public class CheckoutOverview {

    private static final By title = By.className("title");
    private static  final By cartItem =  By.className("cart_item");

    private static final  By buttonFinish= By.id("finish");


    public static void waitTillPageLoad() {
        ElementHandler.waitTillPageLoad(title);
    }

    public static Set<ProductData> getCartItems(){
        return ProductView.getProducts(cartItem);
    }

    public static void completePurchase(){
        ElementHandler.click(buttonFinish);
    }
}
