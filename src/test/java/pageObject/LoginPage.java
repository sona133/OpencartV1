package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage 
{

	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	// Add locators for the login page elements here
	@FindBy(xpath = "//input[@name='username']")
	WebElement txtUsername;
	
	@FindBy(xpath = "//input[@name='password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@value='Log In']")
	WebElement btnLogin;
	
	
	
	
	public void enterUsername(String username) {
		txtUsername.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void clickLoginButton() {
		btnLogin.click();
	}
	
	
}
	


