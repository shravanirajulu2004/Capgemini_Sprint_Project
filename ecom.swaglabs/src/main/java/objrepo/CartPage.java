package objrepo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    WebDriver driver;

    @FindBy(id = "continue-shopping")
    private WebElement Continue;

    @FindBy(id = "checkout")
    private WebElement checkout;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getContinue() {
        return Continue;
    }

    public WebElement getCheckout() {
        return checkout;
    }

    public List<WebElement> getCartItems() {
        return cartItems;
    }

    public void clickContinueShopping() {
        Continue.click();
    }

    public void clickCheckout() {
        checkout.click();
    }

    public int getCartItemCount() {
        return driver.findElements(
                By.className("cart_item")
        ).size();
    }

    public boolean isProductPresent(String productName) {

        By product = By.xpath(
            "//div[contains(@class,'cart_item')]" +
            "[.//div[contains(@class,'inventory_item_name')" +
            " and normalize-space()='" + productName + "']]"
        );

        return !driver.findElements(product).isEmpty();
    }

    public void removeProduct(String productName) {

        By removeButton = By.xpath(
            "//div[contains(@class,'cart_item')]" +
            "[.//div[contains(@class,'inventory_item_name')" +
            " and normalize-space()='" + productName + "']]" +
            "//button[contains(@id,'remove')]"
        );

        driver.findElement(removeButton).click();
    }
}

