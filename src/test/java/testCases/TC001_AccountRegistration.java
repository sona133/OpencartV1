package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistration extends BaseClass
{
	
	@Test (groups = {"regression","master"}) //Grouping the test case
	public void testAccountRegistration() 
	{
		try {
		logger.info("****Starting Account Registration Test****");
		
		// Create an instance of HomePage and click on the Register link
		HomePage homePage = new HomePage(driver);
		homePage.ClickOnRegister();
		logger.info("Clicked on Register link");
		
		// Create an instance of AccountRegistrationPage and fill in the registration form
		AccountRegistrationPage registrationPage = new AccountRegistrationPage(driver);
		registrationPage.fillRegistrationForm(randomString().toUpperCase(), randomString().toUpperCase(), "123 Main St", "Anytown", "CA", "12345", "555-1234", randomNumber(), randomString().toUpperCase(), "password123", "password123");
		logger.info("Filled in the registration form");
		String successMessage = registrationPage.successMessage();
		Assert.assertEquals(successMessage, "Your account was created successfully. You are now logged in.");
		logger.info("****Finished Account Registration Test****");
		
		}
		catch(Exception e) {
			logger.error("Error during account registration test: " + e.getMessage());
		}
		
		finally {
			logger.info("****Finished Account Registration Test without any error****");
		}
	}
	
}



