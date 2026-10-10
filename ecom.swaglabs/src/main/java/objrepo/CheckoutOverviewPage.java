package objrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage {

    WebDriver driver;

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Product Name
    @FindBy(className = "inventory_item_name")
    private WebElement productName;

    public String getProductName() {
        return productName.getText();
    }

    // Payment Information
    @FindBy(className = "summary_info")
    private WebElement summaryInformation;

    public String getSummaryInformation() {
        return summaryInformation.getText();
    }

    // Subtotal
    @FindBy(className = "summary_subtotal_label")
    private WebElement subtotal;

    public String getSubtotal() {
        return subtotal.getText();
    }

    // Tax
    @FindBy(className = "summary_tax_label")
    private WebElement tax;

    public String getTax() {
        return tax.getText();
    }

    // Total
    @FindBy(className = "summary_total_label")
    private WebElement total;

    public String getTotal() {
        return total.getText();
    }

    // Finish Button
    @FindBy(id = "finish")
    private WebElement finishBtn;

    public void clickFinishButton() {
        finishBtn.click();
    }

    // Cancel Button
    @FindBy(id = "cancel")
    private WebElement cancelBtn;

    public void clickCancelButton() {
        cancelBtn.click();
    }
}