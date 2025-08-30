package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountOverview extends BasePage
{

	public MyAccountOverview(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath = "//h1[normalize-space()='Accounts Overview']")
	WebElement HomePageAccountOverview;
	
	@FindBy(xpath="//a[normalize-space()='Bill Pay']")
	WebElement BillPayLink;
	
	@FindBy(xpath="//a[normalize-space()='Log Out']")
	WebElement LogOutLink;
	
	public String getHomePageAccountOverviewText()
	{
		return HomePageAccountOverview.getText();
	}
	public boolean isHomePageAccountOverviewDisplayed() {
		try {
			return (HomePageAccountOverview.isDisplayed());
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickBillPayLink()
	{
		BillPayLink.click();
	}
	
	public void clickLogOutLink()
	{
		LogOutLink.click();
	}
}