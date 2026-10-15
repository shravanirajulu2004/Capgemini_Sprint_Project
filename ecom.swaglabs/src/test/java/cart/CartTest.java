package cart;

import org.testng.Assert;
import org.testng.annotations.Test;

import genericutility.BaseClass;
import genericutility.ExcelUtility;
import genericutility.ScreenshotUtility;
import objrepo.CartPage;
import objrepo.InventoryPage;

public class CartTest extends BaseClass {

    ExcelUtility eu = new ExcelUtility();

    // Read product name from Excel
    public String getProductName() throws Exception {

        return eu.getDataFromExcel(
                "CartData.xlsx",
                "Sheet1",
                1,
                0
        );
    }

 
    // Functional
    // Verify that the selected product is displayed correctly in Cart

    @Test(groups = {"functional", "positive"})
    public void verifyProductDisplayedInCart() throws Exception {

        InventoryPage ip = new InventoryPage(driver);

        String productName = getProductName();

        // Add product to cart
        ip.clickAddToCart(productName);

        // Open Cart
        ip.clickCart();

        CartPage cp = new CartPage(driver);

        // Verify product is displayed
        Assert.assertTrue(
                cp.isProductPresent(productName),
                "Selected product is not displayed in Cart"
        );
    }

    // Functional
    // Verify that a product can be removed from Cart


    @Test(groups = {"functional", "positive"})
    public void removeProductFromCart() throws Exception {

        InventoryPage ip = new InventoryPage(driver);

        String productName = getProductName();

        // Add product
        ip.clickAddToCart(productName);

        // Open Cart
        ip.clickCart();

        CartPage cp = new CartPage(driver);

        // Verify product is present
        Assert.assertTrue(
                cp.isProductPresent(productName),
                "Product is not available in Cart"
        );

        // Remove product
        cp.removeProduct(productName);

        // Verify product is removed
        Assert.assertFalse(
                cp.isProductPresent(productName),
                "Product was not removed from Cart"
        );
    }
  
    // Integration
    // Verify integration between Cart page and Checkout page

    @Test(groups = {"integration", "positive"})
    public void cartToCheckoutIntegration() throws Exception {

        InventoryPage ip = new InventoryPage(driver);

        String productName = getProductName();

        // Add product
        ip.clickAddToCart(productName);

        // Open Cart
        ip.clickCart();

        CartPage cp = new CartPage(driver);

        // Verify product is available
        Assert.assertTrue(
                cp.isProductPresent(productName),
                "Product is not available in Cart"
        );

        // Click Checkout
        cp.clickCheckout();

        // Verify Checkout Information page
        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-one"),
                "User was not redirected to Checkout Information page"
        );
    }
    
    // Smoke
    // Verify that Cart page is accessible

    @Test(groups = {"smoke", "positive"})
    public void verifyCartPageAccessible() {

        InventoryPage ip = new InventoryPage(driver);

        // Click Cart icon
        ip.clickCart();

        // Verify Cart page
        Assert.assertTrue(
                driver.getCurrentUrl().contains("cart"),
                "Cart page did not load successfully"
        );
    }


    // negative
    
    @Test(groups = {"negative"})
    public void verifyRemovedProductNotDisplayed() throws Exception {

        InventoryPage ip = new InventoryPage(driver);

        String productName = getProductName();

        // Add product
        ip.clickAddToCart(productName);

        // Open Cart
        ip.clickCart();

        CartPage cp = new CartPage(driver);

        // Verify product is initially present
        Assert.assertTrue(
                cp.isProductPresent(productName),
                "Product was not added to Cart"
        );

        // Remove product
        cp.removeProduct(productName);

        // Verify product is removed
        Assert.assertFalse(
                cp.isProductPresent(productName),
                "Removed product is still displayed in Cart"
        );

        // Return to Product Page
        cp.clickContinueShopping();

        // Revisit Cart Page
        ip.clickCart();

        // Verify removed product does not come back
        Assert.assertFalse(
                new CartPage(driver).isProductPresent(productName),
                "Removed product reappeared in Cart"
        );

    }
}