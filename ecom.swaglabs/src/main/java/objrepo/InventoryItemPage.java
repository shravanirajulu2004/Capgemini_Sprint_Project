package objrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryItemPage {

    WebDriver driver;

    public InventoryItemPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "inventory_details_name")
    private WebElement productName;

    @FindBy(className = "inventory_details_desc")
    private WebElement productDescription;

    @FindBy(className = "inventory_details_price")
    private WebElement productPrice;

    @FindBy(xpath = "//button[contains(@id,'add-to-cart')]")
    private WebElement addToCart;

    @FindBy(xpath = "//button[contains(@id,'remove')]")
    private WebElement remove;

    @FindBy(id = "back-to-products")
    private WebElement backToProducts;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    public String getProductName() {
        return productName.getText();
    }

    public String getProductDescription() {
        return productDescription.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public void clickAddToCart() {
        addToCart.click();
    }

    public void clickRemove() {
        remove.click();
    }

    public void clickBackToProducts() {
        backToProducts.click();
    }

    public void clickCart() {
        cartIcon.click();
    }
}