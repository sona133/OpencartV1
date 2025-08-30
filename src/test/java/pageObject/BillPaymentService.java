package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BillPaymentService extends BasePage
{

	public BillPaymentService(WebDriver driver) 
	{
		super(driver);
		
		
	}
	
	@FindBy(xpath="//input[@name='payee.name']")
	WebElement inputPayeeName;
	
	@FindBy(xpath="//input[@name='payee.address.street']")
	WebElement inputPayeeAddress;
	
	@FindBy(xpath="//input[@name='payee.address.city']")
	WebElement inputPayeeCity;
	
	@FindBy(xpath="//input[@name='payee.address.state']")
	WebElement inputPayeeState;
	
	@FindBy(xpath="//input[@name='payee.address.zipCode']")
	WebElement inputPayeeZipCode;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/div[1]/form[1]/table[1]/tbody[1]/tr[6]/td[2]/input[1]")
	WebElement inputPayeePhoneNumber;
	
	@FindBy(xpath="//input[@name='payee.accountNumber']")
	WebElement inputPayeeAccountNumber;
	
	@FindBy(xpath="//input[@name='verifyAccount']")
	WebElement inputVerifyAccountNumber;
	
	@FindBy(xpath="//input[@name='amount']")
	WebElement inputAmount;
	
	@FindBy(xpath="//select[@name='fromAccountId']")
	WebElement inputFromAccount;
	
	@FindBy(xpath="//input[@value='Send Payment']")
	WebElement btnSendPayment;
	
	public void fillBillPaymentForm(String payeeName, String payeeAddress, String payeeCity, String payeeState, String payeeZipCode, String payeePhoneNumber, String payeeAccountNumber, String verifyAccountNumber, String amount) 
	{
		inputPayeeName.sendKeys(payeeName);
		inputPayeeAddress.sendKeys(payeeAddress);
		inputPayeeCity.sendKeys(payeeCity);
		inputPayeeState.sendKeys(payeeState);
		inputPayeeZipCode.sendKeys(payeeZipCode);
		inputPayeePhoneNumber.sendKeys(payeePhoneNumber);
		inputPayeeAccountNumber.sendKeys(payeeAccountNumber);
		inputVerifyAccountNumber.sendKeys(verifyAccountNumber);
		inputAmount.sendKeys(amount);
		selectFromDropdown(inputFromAccount);
	}

	public void selectFromDropdown(WebElement inputFromAccount2)
	{
		try {
			inputFromAccount2.click();
		} catch (Exception e) {
			
		}
		
	}

	public void submitBillPayment()
	{
		btnSendPayment.click();
		
	}

}
