package pages.views;

import core.ElementHandler;
import models.ProductData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Set;
import java.util.stream.Collectors;

public class ProductView {


    private static final By INVENTORY_ITEM_NAME = By.className("inventory_item_name");
    private static final By INVENTORY_ITEM_DESC = By.className("inventory_item_desc");
    private static final By INVENTORY_ITEM_PRICE = By.className("inventory_item_price");



    public static Set<ProductData> getProducts(By wrapper) {
        return ElementHandler.findElements(wrapper).stream().map(element -> {
            String name = element.findElement(INVENTORY_ITEM_NAME).getText();
            String description = element.findElement(INVENTORY_ITEM_DESC).getText();
            String price = element.findElement(INVENTORY_ITEM_PRICE).getText();
            String cartId = "add-to-cart-" + name.replace(" ", "-").toLowerCase();
            return ProductData.builder().description(description).name(name).price(price).cartButton(cartId).build();
        }).collect(Collectors.toSet());
    }
}
