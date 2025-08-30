package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.LoginPage;
import pageObject.MyAccountOverview;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDrivenTest extends BaseClass
{
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class) //getting dataprovider from different classes
	public void testLoginDataDriver(String uname,String pwd ,String expctResult) throws InterruptedException 
	{
		logger.info("****Starting Login Data Driven Test****");
		
		try {
			
			//Logging in to the application
			LoginPage login = new LoginPage(driver);
			login.enterUsername(uname);
			login.enterPassword(pwd);
			login.clickLoginButton();
			
			//MyAccount Overview
			MyAccountOverview myAccount = new MyAccountOverview(driver);
			boolean targetpage = myAccount.isHomePageAccountOverviewDisplayed();
			
			// Validate the login result based on expected outcome
			// If expected result is "Valid", we expect to be on the My Account page
			// If expected result is "Invalid", we expect not to be on the My Account page
			// If expected result is neither "Valid" nor "Invalid", we fail the test
			
			logger.info("****Starting Login Data Driven Test****");
			if (expctResult.equalsIgnoreCase("Valid")) 
			{
				if (targetpage == true) 
				{
					myAccount.clickLogOutLink();
					Assert.assertTrue(true, "Login successful");
				} 
				else 
				{
					Assert.assertTrue(false, "Login failed");
				}
			} 
			else if (expctResult.equalsIgnoreCase("Invalid")) 
			{
				if (targetpage == false) 
				{
					Assert.assertTrue(true, "Login failed as expected");
				} 
				else 
				{
					Assert.assertTrue(false, "Login should have failed but it was successful");
				}
			}
			else 
			{
				Assert.fail("Invalid expected result provided: " + expctResult);
			}
		} catch (Exception e)
		{
			Assert.fail();
		}
			Thread.sleep(3000); //Adding sleep to see the result
			logger.info("****Finished Login Data Driven Test without any error****");
	}

}
