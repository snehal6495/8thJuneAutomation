package coverFoxPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class C_CoverFoxMemberDetailsPage 
{
	//1] variable declaration -
	@FindBy(id = "Age-You") private WebElement ageDropDown;
	@FindBy(className = "next-btn") private WebElement nextButton;
	
	//2] constructor -
	public C_CoverFoxMemberDetailsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//3] methods -
	public void handleAgeDropDown(String age)//zero parameterized mtd
	{
		Reporter.log("Handling Age DropDown..", true);
		Select s= new Select(ageDropDown);//here we pass variable
		s.selectByValue(age+"y");
	}
	public void clickOnNextButton()
	{
		Reporter.log("Clicking on Next Button..", true);
		nextButton.click();//variable.method 
	}

}
