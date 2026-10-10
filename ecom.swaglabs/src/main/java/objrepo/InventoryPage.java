package objrepo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {

    WebDriver driver;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "inventory_item")
    private List<WebElement> products;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(id = "shopping_cart_container")
    private WebElement cartButton;


    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    // Page title
    public String getPageTitle() {
        return pageTitle.getText();
    }


    // Number of products
    public int getProductCount() {
        return products.size();
    }


    // Product names
    public List<WebElement> getProductNames() {
        return productNames;
    }


    // Product prices
    public List<WebElement> getProductPrices() {
        return productPrices;
    }
 
    public void clickAddToCart(String productName) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        By productCard = By.xpath(
            "//div[contains(@class,'inventory_item')]" +
            "[.//div[contains(@class,'inventory_item_name')" +
            " and normalize-space()='" + productName + "']]"
        );

        WebElement product = wait.until(
            ExpectedConditions.visibilityOfElementLocated(productCard)
        );

        WebElement button = product.findElement(
            By.xpath(".//button")
        );

        wait.until(
            ExpectedConditions.elementToBeClickable(button)
        );

        if (button.getText().equalsIgnoreCase("Add to cart")) {
            button.click();
        }
    }

    // Dynamic Remove From Cart
    public void clickRemoveFromCart(String productName) {

        String product = productName
                .toLowerCase()
                .replace(" ", "-");

        By removeButton =
                By.id("remove-" + product);

        driver.findElement(removeButton).click();
    }


    // Dynamic Product Selection
    public void clickProduct(String productName) {

        By productLink = By.xpath(
                "//div[contains(@class,'inventory_item_name') and normalize-space()='"
                + productName + "']"
        );

        driver.findElement(productLink).click();
    }


    // Dynamic Sorting
    public void sortProducts(String value) {

        Select select = new Select(sortDropdown);

        select.selectByValue(value);
    }


    // Open Cart
    public void clickCart() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
            ExpectedConditions.elementToBeClickable(cartButton)
        ).click();
    }


    // Check Product Displayed
    public boolean isProductDisplayed(String productName) {

        By product = By.xpath(
                "//div[contains(@class,'inventory_item_name') and normalize-space()='"
                + productName + "']"
        );

        return driver.findElement(product).isDisplayed();
    }
}