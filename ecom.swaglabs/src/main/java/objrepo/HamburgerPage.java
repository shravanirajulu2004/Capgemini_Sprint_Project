package objrepo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HamburgerPage {

    WebDriver driver;
    WebDriverWait wait;

    public HamburgerPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By hamburgerButton =
            By.id("react-burger-menu-btn");

    private By closeButton =
            By.id("react-burger-cross-btn");

    private By allItems =
            By.id("inventory_sidebar_link");

    private By about =
            By.id("about_sidebar_link");

    private By logout =
            By.id("logout_sidebar_link");

    private By resetAppState =
            By.id("reset_sidebar_link");


    public void clickHamburger() {

        wait.until(
            ExpectedConditions.elementToBeClickable(hamburgerButton)
        ).click();
    }


    public void clickClose() {

        wait.until(
            ExpectedConditions.elementToBeClickable(closeButton)
        ).click();
    }


    public void clickAllItems() {

        wait.until(
            ExpectedConditions.elementToBeClickable(allItems)
        ).click();
    }


    public void clickAbout() {

        wait.until(
            ExpectedConditions.elementToBeClickable(about)
        ).click();
    }


    public void clickLogout() {
        wait.until(
            ExpectedConditions.elementToBeClickable(logout)
        ).click();
    }


    public void clickResetAppState() {

        wait.until(
            ExpectedConditions.elementToBeClickable(resetAppState)
        ).click();

        // Verify that cart badge disappears
        wait.until(
            ExpectedConditions.invisibilityOfElementLocated(
                By.id("shopping_cart_badge")
            )
        );
    }
}

