package e2e;

import core.util.JsonReader;
import models.ProductData;
import models.UserData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import pages.views.ProductView;

import java.util.List;
import java.util.Set;

public class TestLogin extends TestBase {

    ProductData[] products = JsonReader.readJsonFile(ProductData[].class);
    List<ProductData> productDataList = JsonReader.getJsonFileData(ProductData[].class);
    UserData[] users =  JsonReader.readJsonFile(UserData[].class);

    @Test
    public void testLoginPage() {
        LoginPage.login("standard_user", "secret_sauce");
    }


    @Test(dependsOnMethods = {"testLoginPage"})
    public void testProduct() {
        ProductPage.waitTillPageLoad();
        int cartCount = ProductPage.addItemsToCart(productDataList);
        Assert.assertEquals(cartCount, products.length);
    }


    @Test(dependsOnMethods = {"testProduct"})
    public void testCartPage() {
        ProductPage.navigateToCartPage();
        CartPage.waitTillPageLoad();
        Set<ProductData> cartItems = CartPage.getCartItems();
        Assert.assertEquals(cartItems.size(), products.length);
        Assert.assertTrue(cartItems.containsAll(productDataList));
    }

    @Test(dependsOnMethods = {"testCartPage"})
    public void testCheckout(){
        CartPage.navigateToCheckout();
        CheckoutInfoPage.waitTillPageLoad();
        CheckoutInfoPage.enterUserDetails(users[0]);
    }

    @Test(dependsOnMethods = {"testCheckout"})
    public void testCheckoutOverviewPage(){
        CheckoutOverview.waitTillPageLoad();
        Set<ProductData> cartItems = CheckoutOverview.getCartItems();
        CheckoutOverview.completePurchase();

        double totalUIPrice = ProductView.getTotalPrice(cartItems);
        double totalCalculatedPrice = ProductView.getTotalPrice(productDataList);

        Assert.assertEquals(cartItems.size(), products.length);
        Assert.assertEquals(totalUIPrice,totalCalculatedPrice);

    }
}
