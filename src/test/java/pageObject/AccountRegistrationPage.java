package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountRegistrationPage extends BasePage{
	 
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	// Define locators for the elements on the Account Registration page
	// For example, you might have fields for first name, last name, email, etc.
	 @FindBy(id = "customer.firstName")
	 WebElement inputFirstName;	
	 @FindBy(id = "customer.lastName")
	 WebElement inputLastName;
	 @FindBy(id = "customer.address.street")
	 WebElement inputAddressStreet;
	 @FindBy(id = "customer.address.city")
	 WebElement inputAddressCity;
	 @FindBy(id = "customer.address.state")
	 WebElement inputAddressState;
	 @FindBy(id = "customer.address.zipCode")	
	 WebElement inputAddressZip;
	 @FindBy(id = "customer.phoneNumber")	
	 WebElement inputPhoneNumber;	
	 @FindBy(id = "customer.ssn")
	 WebElement inputSSN;
	 @FindBy(id = "customer.username")
	 WebElement inputUsername;
	 @FindBy(id = "customer.password")
	 WebElement inputPassword;
	 @FindBy(id = "repeatedPassword")
	 WebElement inputConfirmPassword;
	 @FindBy(xpath = "//input[@value='Register']")
	 WebElement btnRegister;
	 @FindBy(xpath = "//p[contains(text(),'Your account was created successfully. You are now')]")
	 WebElement successMessage;
	 
	 	
	 // Method to fill in the registration form
	 	
	 	public void fillRegistrationForm(String firstName, String lastName, String street, String city, String state, String zip, String phoneNumber, String ssn, String username, String password, String confirmPassword) {
	 					inputFirstName.sendKeys(firstName);
	 					inputLastName.sendKeys(lastName);
	 					inputAddressStreet.sendKeys(street);
	 					inputAddressCity.sendKeys(city);
	 					inputAddressState.sendKeys(state);
	 					inputAddressZip.sendKeys(zip);
	 					inputPhoneNumber.sendKeys(phoneNumber);
	 					inputSSN.sendKeys(ssn);
	 					inputUsername.sendKeys(username);
	 					inputPassword.sendKeys(password);
	 					inputConfirmPassword.sendKeys(confirmPassword);
	 					btnRegister.click();
	 	
	 					
	 	}
	 	
	 	public String successMessage() {
	 		try {
	 			return successMessage.getText();
	 		} catch (Exception e) {
	 			return (e.getMessage());
	 		}
	 	}
	 
	
}
