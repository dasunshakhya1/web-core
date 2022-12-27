package pages;

import core.ElementHandler;
import models.ProductData;
import org.openqa.selenium.By;
import pages.views.ProductView;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProductPage {

    private static final By INVENTORY = By.className("inventory_item_description");
    private static final By FOOTER = By.className("footer");

    private static final By CART = By.id("shopping_cart_container");
    private static final By SHOPPING_CART_BADGE = By.className("shopping_cart_badge");


    public static void waitTillPageLoad() {
        ElementHandler.waitTillPageLoad(FOOTER);
    }

    private static Set<ProductData> getProducts() {
        return ProductView.getProducts(INVENTORY);
    }

    public static int addItemsToCart(List<ProductData> productData) {
        Set<ProductData> productDataSet = getProducts();
        for (ProductData productDatum : productData) {
            Optional<ProductData> prd = productDataSet.stream().filter(p -> p.getName().equalsIgnoreCase(productDatum.getName())).findFirst();
            prd.ifPresent(data -> ElementHandler.click(By.id(data.getCartButton())));
        }
        return Integer.parseInt(ElementHandler.getElementText(SHOPPING_CART_BADGE));
    }

    public static void navigateToCartPage(){
        ElementHandler.click(CART);
    }
}
