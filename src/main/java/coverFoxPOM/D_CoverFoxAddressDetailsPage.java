package coverFoxPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.aventstack.extentreports.model.Report;

public class D_CoverFoxAddressDetailsPage 
{
	//1] variable declaration -
	@FindBy(className = "mp-input-text") private WebElement pincodeField;
	@FindBy(id = "want-expert") private WebElement mobnoField;
	@FindBy(className = "next-btn") private WebElement continueButton;
					//for 2nd TC - different class for different TC - Error Messages
	@FindBy(xpath = " //div[text()=' Please enter a valid pincode ']")
	private WebElement pincodeErrMsg;
	
	@FindBy(xpath = " //div[text()=' Please enter a valid mobile no. ']") 
	private WebElement mobnoErrMsg;
	
	//2] constructor -
	public D_CoverFoxAddressDetailsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//3] methods -
	public void enterPincode(String pincode)
	{
		Reporter.log("Entering Pincode..", true);//for purpose of check point thats why we use reporter here
		pincodeField.sendKeys(pincode);
	}
	
	public void enterMobno(String mobno)
	{
		Reporter.log("Entering Mobile Number..", true);
		mobnoField.sendKeys(mobno);
	}
	
	public void clickOnContinueButton()
	{
		Reporter.log("Clicking on Continue Button..", true);
		continueButton.click();
	}
	
	public String getPinErrorMsg()
	{
		String actualText = pincodeErrMsg.getText();
		return actualText;
	}
	
	public String getMobnoErrorMsg()
	{
		String actualText = mobnoErrMsg.getText();
		return actualText;
	}
}









