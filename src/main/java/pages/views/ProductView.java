package pages.views;

import core.ElementHandler;
import models.ProductData;
import org.openqa.selenium.By;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductView {


    private static final By inventoryItemName = By.className("inventory_item_name");
    private static final By inventoryItemDesc = By.className("inventory_item_desc");
    private static final By inventoryPrice = By.className("inventory_item_price");



    public static Set<ProductData> getProducts(By wrapper) {
        return ElementHandler.findElements(wrapper).stream().map(element -> {
            String name = element.findElement(inventoryItemName).getText();
            String description = element.findElement(inventoryItemDesc).getText();
            String price = element.findElement(inventoryPrice).getText();
            String cartId = "add-to-cart-" + name.replace(" ", "-").toLowerCase();
            return ProductData.builder().description(description).name(name).price(price).cartButton(cartId).build();
        }).collect(Collectors.toSet());
    }

    public static double getTotalPrice(Collection<ProductData> productData) {
        double total = 0;

        for (ProductData pr : productData) {
            total += pr.getPrice();
        }

        return total;
    }
}
