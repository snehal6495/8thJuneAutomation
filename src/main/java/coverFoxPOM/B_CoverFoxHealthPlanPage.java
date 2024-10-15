package coverFoxPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class B_CoverFoxHealthPlanPage 
{
	//1] variable declaration -
	@FindBy(className = "next-btn") private WebElement nextButton;
	
	//2] constructor -
	public B_CoverFoxHealthPlanPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//3] methods -
	public void clickOnNextButton()
	{
		Reporter.log("Clicking on Next Button..", true);
		nextButton.click();
	}

}
