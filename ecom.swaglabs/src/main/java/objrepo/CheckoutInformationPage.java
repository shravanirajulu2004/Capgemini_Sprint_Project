package objrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutInformationPage {

    WebDriver driver;

    public CheckoutInformationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // First Name
    @FindBy(id = "first-name")
    private WebElement firstName;

    public void getFirstName(String value) {
        firstName.sendKeys(value);
    }

    // Last Name
    @FindBy(id = "last-name")
    private WebElement lastName;

    public void getLastName(String value) {
        lastName.sendKeys(value);
    }

    // Postal Code
    @FindBy(id = "postal-code")
    private WebElement postalCode;

    public void getPostalCode(String value) {
        postalCode.sendKeys(value);
    }

    // Continue Button
    @FindBy(id = "continue")
    private WebElement continueBtn;

    public void clickContinueButton() {
        continueBtn.click();
    }

    // Cancel Button
    @FindBy(id = "cancel")
    private WebElement cancelBtn;

    public void clickCancelButton() {
        cancelBtn.click();
    }

    // Error Message
    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}