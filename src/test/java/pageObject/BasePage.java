package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	WebDriver driver;
	
	//Created a constructor to initialize the WebDriver and PageFactory
	public BasePage(WebDriver driver) {
		WebDriverManager.edgedriver().setup(); // Ensure WebDriverManager is set up for ChromeDriver
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
}
