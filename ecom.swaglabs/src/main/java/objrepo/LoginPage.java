package objrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	
	//username
	@FindBy(xpath = "//input[@id=\"user-name\"]")
	private WebElement username;
	public WebElement getUsername() {
		 return username;
	}
	
	//password
	@FindBy(xpath = "//input[@id=\"password\"]")
	private WebElement password;
	public WebElement getPassword() {
		return password;
	}
	
	//loginbtn
	@FindBy(xpath = "//input[@id=\"login-button\"]")
	private WebElement loginbtn;
	public WebElement getLoginbtn() {
		return loginbtn;
	}
	
	public void login(String un,String pwd) {
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginbtn.click();
	}
}