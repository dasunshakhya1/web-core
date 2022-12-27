package pages;

import core.ElementHandler;
import models.ProductData;
import org.openqa.selenium.By;
import pages.views.ProductView;

import java.util.Set;

public class CartPage {



    private static final By CHECKOUT = By.id("checkout");
    private static  final By CART_ITEM =  By.className("cart_item");



    public static void waitTillPageLoad(){
        ElementHandler.waitTillPageLoad(CHECKOUT);
    }

    public static Set<ProductData> getCartItems(){
        return ProductView.getProducts(CART_ITEM);
    }

}
