package testCases;

import pageObject.MyAccountOverview;
import testBase.BaseClass;

import org.testng.annotations.Test;

import pageObject.BillPaymentService;
import pageObject.LoginPage;

public class TC004_BillPaymentService extends BaseClass
{
	@Test(groups = {"regression","master","sanity"}) //Grouping the test case
	public void testBillPaymentService() 
	{
		logger.info("****Starting Bill Payment Service Test****");
		
		try {
			
			//Logging in to the application
			LoginPage login = new LoginPage(driver);
			login.enterUsername(p.getProperty("username"));
			login.enterPassword(p.getProperty("password"));
			login.clickLoginButton();
			
			// Navigate to Bill Payment Service
			MyAccountOverview myAccount = new MyAccountOverview(driver);
			myAccount.clickBillPayLink();
			
			// Fill in the bill payment form
			BillPaymentService billPayment = new BillPaymentService(driver);
			billPayment.fillBillPaymentForm(randomString().toUpperCase(), randomString().toUpperCase(), randomString().toUpperCase(), "IL", "62701", "555-555-5555","123456789", "123456789",randomNumber());
			
			// Submit the bill payment
			billPayment.submitBillPayment();
			
			
			logger.info("****Finished Bill Payment Service Test****");
		} catch (Exception e) {
			logger.error("Error during bill payment service test: " + e.getMessage());
		}
		
		finally {
			logger.info("****Finished Bill Payment Service Test without any error****");
		}
	}

}
