package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.LoginPage;
import pageObject.MyAccountOverview;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
 
{
	@Test (groups = {"sanity","master"}) //Grouping the test case
	public void testLogin()
	{
		
		try {
			logger.info("****Starting Login Test****");
			
			//Logging in to the application
			LoginPage login = new LoginPage(driver);
			login.enterUsername(p.getProperty("username"));
			login.enterPassword(p.getProperty("password"));
			login.clickLoginButton();
			
			//MyAccount Overview
			MyAccountOverview myAccount = new MyAccountOverview(driver);
			boolean targetpage = myAccount.isHomePageAccountOverviewDisplayed();
			Assert.assertTrue(targetpage, "Login failed");//Assert.assertEquals(targetpage, true,"Login failed");
			
			//LouOut from the application
			myAccount.clickLogOutLink();
		} 
			catch (Exception e) 
		{
			Assert.fail();
		}
			
		
		
			logger.info("****Finished Login Test without any error****");
		}
		
}
	


