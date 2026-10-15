package checkout;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import genericutility.BaseClass;
import genericutility.ExcelUtility;
import genericutility.ScreenshotUtility;
import objrepo.CartPage;
import objrepo.CheckoutInformationPage;
import objrepo.CheckoutOverviewPage;
import objrepo.InventoryPage;

public class CheckoutTest extends BaseClass {
	ExcelUtility eu = new ExcelUtility();
	private void addProductAndOpenCart(
	        InventoryPage ip,
	        String productName) {

	    ip.clickAddToCart(productName);
	    ip.clickCart();
	}

	//FUNCTIONAL
	
	
	@Test(groups = {"functional", "positive"})
	public void enterValidCheckoutInformation() throws Exception {

	    InventoryPage ip = new InventoryPage(driver);

	    String productName = eu.getDataFromExcel(
	            "CartData.xlsx", "Sheet1", 1, 0
	        );

//	    ip.clickAddToCart(productName);
//	    ip.clickCart();
	    addProductAndOpenCart(ip, productName);

	    CartPage cp = new CartPage(driver);
	    cp.clickCheckout();

	    CheckoutInformationPage cip =
	            new CheckoutInformationPage(driver);

	    String firstName =
	            eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 1, 0);

	    String lastName =
	            eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 1, 1);

	    String postalCode =
	            eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 1, 2);

	    cip.getFirstName(firstName);
	    cip.getLastName(lastName);
	    cip.getPostalCode(postalCode);

	    cip.clickContinueButton();

	    CheckoutOverviewPage cop =
	            new CheckoutOverviewPage(driver);

	    Assert.assertTrue(
	        cop.getSummaryInformation().length() > 0,
	        "Checkout Overview was not displayed"
	    );
	}

    // Integration
    // Verify integration between Cart and Checkout Information
	
    @Test(groups = {"integration", "positive"})
    public void cartToCheckoutInformation() throws IOException {

        InventoryPage ip = new InventoryPage(driver);

        String productName = eu.getDataFromExcel("CartData.xlsx", "Sheet1", 1, 0);

//        ip.clickAddToCart(productName);
//        ip.clickCart();
        addProductAndOpenCart(ip, productName);

        CartPage cp = new CartPage(driver);

        Assert.assertTrue(
            cp.isProductPresent(productName),
            "Product is not present in Cart"
        );

        cp.clickCheckout();

        CheckoutInformationPage cip = new CheckoutInformationPage(driver);
        String firstName =
                eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 1, 0);

        String lastName =
                eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 1, 1);

        String postalCode =
                eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 1, 2);

        cip.getFirstName(firstName);
        cip.getLastName(lastName);
        cip.getPostalCode(postalCode);
        cip.clickContinueButton();

        CheckoutOverviewPage cop = new CheckoutOverviewPage(driver);

        Assert.assertTrue(
            cop.getSummaryInformation().length() > 0,
            "User was not navigated to Checkout Overview"
        );
    }


    // Integration
    // Verify integration between Checkout Overview and Order Confirmation
   
    @Test(groups = {"integration", "positive"})
    public void checkoutOverviewToOrderConfirmation() throws IOException {

        InventoryPage ip = new InventoryPage(driver);

        String productName = eu.getDataFromExcel("CartData.xlsx", "Sheet1", 3, 0);

//        ip.clickAddToCart(productName);
//        ip.clickCart();
        addProductAndOpenCart(ip, productName);

        CartPage cp = new CartPage(driver);
        cp.clickCheckout();

        CheckoutInformationPage cip = new CheckoutInformationPage(driver);

        String firstName =
                eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 1, 0);

        String lastName =
                eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 1, 1);

        String postalCode =
                eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 1, 2);

        cip.getFirstName(firstName);
        cip.getLastName(lastName);
        cip.getPostalCode(postalCode);
        cip.clickContinueButton();

        CheckoutOverviewPage cop = new CheckoutOverviewPage(driver);

        Assert.assertTrue(
            cop.getSummaryInformation().length() > 0,
            "Checkout Overview is not displayed"
        );

        cop.clickFinishButton();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("checkout-complete"),
            "Order Confirmation page was not displayed"
        );
    }


  
    // System
    // Verify that user can complete the purchase successfully
    
    @Test(groups = {"system", "positive"})
    public void completePurchaseSuccessfully() throws IOException {

        InventoryPage ip = new InventoryPage(driver);

        String productName = eu.getDataFromExcel("CartData.xlsx", "Sheet1", 5, 0);

//        ip.clickAddToCart(productName);
//        ip.clickCart();
        addProductAndOpenCart(ip, productName);

        CartPage cp = new CartPage(driver);
        cp.clickCheckout();

        CheckoutInformationPage cip = new CheckoutInformationPage(driver);

        String firstName =
                eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 1, 0);

        String lastName =
                eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 1, 1);

        String postalCode =
                eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 1, 2);

        cip.getFirstName(firstName);
        cip.getLastName(lastName);
        cip.getPostalCode(postalCode);
        cip.clickContinueButton();

        CheckoutOverviewPage cop = new CheckoutOverviewPage(driver);

        cop.clickFinishButton();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("checkout-complete"),
            "Purchase was not completed successfully"
        );
    }


    
    // System
    // Verify complete end-to-end purchase flow
   
    @Test(groups = {"system", "positive"})
    public void endToEndPurchaseFlow() throws IOException {

        InventoryPage ip = new InventoryPage(driver);

        String productName = eu.getDataFromExcel("CartData.xlsx", "Sheet1", 4, 0);

//        // Product
//        ip.clickAddToCart(productName);
//
//        // Cart
//        ip.clickCart();
        addProductAndOpenCart(ip, productName);

        CartPage cp = new CartPage(driver);

        Assert.assertTrue(
            cp.isProductPresent(productName),
            "Product is not present in Cart"
        );

        // Checkout
        cp.clickCheckout();

        // Customer information
        CheckoutInformationPage cip = new CheckoutInformationPage(driver);

        String firstName =
                eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 1, 0);

        String lastName =
                eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 1, 1);

        String postalCode =
                eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 1, 2);

        cip.getFirstName(firstName);
        cip.getLastName(lastName);
        cip.getPostalCode(postalCode);
        cip.clickContinueButton();

        // Overview
        CheckoutOverviewPage cop = new CheckoutOverviewPage(driver);

        Assert.assertEquals(
            cop.getProductName(),
            productName,
            "Wrong product is displayed in Checkout Overview"
        );

        // Finish purchase
        cop.clickFinishButton();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("checkout-complete"),
            "End-to-end purchase was not completed"
        );
    }
 
    // Smoke
    // Verify Checkout page is accessible from Cart
    
    @Test(groups = {"smoke", "positive"})
    public void checkoutPageAccessibleFromCart() throws IOException {

        InventoryPage ip = new InventoryPage(driver);

        String productName = eu.getDataFromExcel("CartData.xlsx", "Sheet1", 1, 0);

//        ip.clickAddToCart(productName);
//        ip.clickCart();
        addProductAndOpenCart(ip, productName);

        CartPage cp = new CartPage(driver);

        Assert.assertTrue(
            cp.isProductPresent(productName),
            "Product is not present in Cart"
        );

        cp.clickCheckout();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("checkout-step-one"),
            "Checkout Information page did not load"
        );
    }


 
    // Negative
    // Verify checkout cannot proceed when First Name is blank

    @Test(groups = {"negative"})
    public void checkoutWithBlankFirstName() throws IOException {

        InventoryPage ip = new InventoryPage(driver);

        String productName = eu.getDataFromExcel("CartData.xlsx", "Sheet1", 3, 0);

//        ip.clickAddToCart(productName);
//        ip.clickCart();
        addProductAndOpenCart(ip, productName);

        CartPage cp = new CartPage(driver);
        cp.clickCheckout();

        CheckoutInformationPage cip = new CheckoutInformationPage(driver);

        String firstName =
                eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 2, 0);

        String postalCode =
                eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 2, 2);

        cip.getFirstName(firstName);
        cip.getPostalCode(postalCode);
        cip.clickContinueButton();

        Assert.assertTrue(
            cip.getErrorMessage().length() > 0,
            "Validation error message was not displayed"
        );
      
    }
    
    // Functional
    // Verify Cancel button on Checkout Information page
   
    @Test(groups = {"functional", "positive"})
    public void cancelCheckout() throws IOException {

        InventoryPage ip = new InventoryPage(driver);

        String productName = eu.getDataFromExcel("CartData.xlsx","Sheet1", 3, 0);

//        ip.clickAddToCart(productName);
//        ip.clickCart();
        addProductAndOpenCart(ip, productName);

        CartPage cp = new CartPage(driver);
        cp.clickCheckout();

        CheckoutInformationPage cip = new CheckoutInformationPage(driver);
        cip.clickCancelButton();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("cart"),
            "User was not redirected to Cart page"
        );

    }


    // Functional
    // Verify Order Summary displays Subtotal, Tax and Total
   
    @Test(groups = {"functional", "positive"})
    public void verifyOrderSummary() throws IOException {

        InventoryPage ip = new InventoryPage(driver);

        String productName = eu.getDataFromExcel("CartData.xlsx", "Sheet1", 5, 0);

//        ip.clickAddToCart(productName);
//        ip.clickCart();
        addProductAndOpenCart(ip, productName);

        CartPage cp = new CartPage(driver);
        cp.clickCheckout();

        CheckoutInformationPage cip = new CheckoutInformationPage(driver);

        String firstName =
                eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 1, 0);

        String lastName =
                eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 1, 1);

        String postalCode =
                eu.getDataFromExcel("Checkout_Data.xlsx", "CustomerData", 1, 2);
        cip.getFirstName(firstName);
        cip.getLastName(lastName);
        cip.getPostalCode(postalCode);
        cip.clickContinueButton();

        CheckoutOverviewPage cop = new CheckoutOverviewPage(driver);

        Assert.assertTrue(
            cop.getSubtotal().startsWith("Item total:"),
            "Subtotal is not displayed"
        );

        Assert.assertTrue(
            cop.getTax().startsWith("Tax:"),
            "Tax is not displayed"
        );

        Assert.assertTrue(
            cop.getTotal().startsWith("Total:"),
            "Total is not displayed"
        );
    }

    // Integration
    // Verify Checkout Information to Checkout Overview
    
    @Test(groups = {"integration", "positive"})
    public void checkoutInformationToOverview() throws IOException {

        InventoryPage ip = new InventoryPage(driver);

        String productName = eu.getDataFromExcel("CartData.xlsx", "Sheet1", 4, 0);

//        ip.clickAddToCart(productName);
//        ip.clickCart();
        addProductAndOpenCart(ip, productName);

        CartPage cp = new CartPage(driver);
        cp.clickCheckout();

        CheckoutInformationPage cip = new CheckoutInformationPage(driver);

        cip.getFirstName("John");
        cip.getLastName("Doe");
        cip.getPostalCode("560001");
        cip.clickContinueButton();

        CheckoutOverviewPage cop = new CheckoutOverviewPage(driver);

        Assert.assertTrue(
            cop.getSummaryInformation().length() > 0,
            "Checkout Overview was not displayed"
        );
    }
}