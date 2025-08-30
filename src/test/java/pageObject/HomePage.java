package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	// Constructor to initialize the HomePage with WebDriver
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//*[@id=\"loginPanel\"]/p[2]/a")
	WebElement lnkRegister;
	
	
	public void ClickOnRegister() {
		lnkRegister.click();		
	}

}
