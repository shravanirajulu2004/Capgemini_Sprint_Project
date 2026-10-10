package inventory;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericutility.BaseClass;
import genericutility.ExcelUtility;
import objrepo.CartPage;
import objrepo.InventoryItemPage;
import objrepo.InventoryPage;

public class InventoryTest extends BaseClass {

    ExcelUtility eu = new ExcelUtility();

    // FUNCTIONAL

    @Test(groups = {"functional", "positive"})
    public void addProductToCart() throws IOException {

        InventoryPage ip = new InventoryPage(driver);

        // Get product dynamically from Excel
        String productName =
                eu.getDataFromExcel(
                        "CartData.xlsx",
                        "Sheet1",
                        1,
                        0
                );

        ip.clickAddToCart(productName);
        ip.clickCart();

        CartPage cp = new CartPage(driver);

        Assert.assertTrue(
                cp.isProductPresent(productName),
                "Product is not displayed in Cart"
        );
    }

    //FUNCTIONAL
    
    @Test(groups = {"functional", "positive"})
    public void verifyProductSorting() {

        InventoryPage ip = new InventoryPage(driver);

        ip.sortProducts("lohi");

        List<WebElement> prices = ip.getProductPrices();

        double firstPrice =
                Double.parseDouble(
                        prices.get(0)
                                .getText()
                                .replace("$", "")
                );

        double lastPrice =
                Double.parseDouble(
                        prices.get(prices.size() - 1)
                                .getText()
                                .replace("$", "")
                );

        Assert.assertTrue(
                firstPrice <= lastPrice,
                "Products are not sorted from low to high"
        );
    }


    // INTEGRATION

    @Test(groups = {"integration", "positive"})
    public void productToProductDetails() throws IOException {

        InventoryPage ip = new InventoryPage(driver);

        // Get product dynamically from Excel
        String productName =
                eu.getDataFromExcel(
                        "CartData.xlsx",
                        "Sheet1",
                        2,
                        0
                );

        ip.clickProduct(productName);

        InventoryItemPage iip =
                new InventoryItemPage(driver);

        Assert.assertEquals(
                iip.getProductName(),
                productName,
                "Wrong product details page opened"
        );

        Assert.assertTrue(
                iip.getProductDescription().length() > 0,
                "Product description is not displayed"
        );

        Assert.assertTrue(
                iip.getProductPrice().startsWith("$"),
                "Product price is not displayed correctly"
        );
    }

    // SYSTEM

    @Test(groups = {"system", "positive"})
    public void sortSelectProductAndAddToCart() throws IOException {

        InventoryPage ip = new InventoryPage(driver);

        // Sort products
        ip.sortProducts("lohi");

        // Get product dynamically from Excel
        String productName =
                eu.getDataFromExcel(
                        "CartData.xlsx",
                        "Sheet1",
                        3,
                        0
                );

        ip.clickProduct(productName);

        InventoryItemPage iip =
                new InventoryItemPage(driver);

        Assert.assertEquals(
                iip.getProductName(),
                productName,
                "Wrong product selected"
        );

        iip.clickAddToCart();

        iip.clickCart();

        CartPage cp = new CartPage(driver);

        Assert.assertTrue(
                cp.isProductPresent(productName),
                "Selected product is not displayed in Cart"
        );
    }


    // SMOKE
    
    @Test(groups = {"smoke", "positive"})
    public void verifyProductsPageLoads() {

        InventoryPage ip = new InventoryPage(driver);

        Assert.assertEquals(
                ip.getPageTitle(),
                "Products",
                "Products page is not displayed"
        );
    }

}