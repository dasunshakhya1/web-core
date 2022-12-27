package e2e;

import core.util.JsonReader;
import models.ProductData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;

import java.util.List;
import java.util.Set;

public class TestLogin extends TestBase {

    ProductData[] products = JsonReader.readJsonFile(ProductData[].class);

    @Test
    public void testLoginPage() {
        LoginPage.login("standard_user", "secret_sauce");
    }


    @Test(dependsOnMethods = {"testLoginPage"})
    public void testProduct() {
        ProductPage.waitTillPageLoad();
        int cartCount = ProductPage.addItemsToCart(List.of(products));
        Assert.assertEquals(cartCount, products.length);
    }


    @Test(dependsOnMethods = {"testProduct"})
    public void testCartPage() {
        ProductPage.navigateToCartPage();
        CartPage.waitTillPageLoad();
        Set<ProductData> cartItems = CartPage.getCartItems();
        Assert.assertEquals(cartItems.size(), products.length);
        Assert.assertTrue(cartItems.containsAll(List.of(products)));
    }
}
