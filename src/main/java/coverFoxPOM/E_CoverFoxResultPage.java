package coverFoxPOM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class E_CoverFoxResultPage 
{
	//1] Variable declaration --> webElement
	@FindBy(xpath = "//div[contains(text(),'matching Health')]") private WebElement resultText;
	@FindBy(className = "plan-card-container") private List<WebElement> planList;
	//bcz we got multiple plans or result or elements in list format
	
	//2] constructor -
	public E_CoverFoxResultPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//3] methods -
	public int getCountFromText()
	{
		Reporter.log("Getting Policy Count From Text Displayed..", true);
		String resultInString = resultText.getText().substring(0,2);
		int countFromText = Integer.parseInt(resultInString);
		return countFromText;//here it change the mtds return type from void to int 
	}
	
	public int getCountFromBanner()
	{
		Reporter.log("Getting Policy Count From Banners Displayed..", true);
		int countFromBanner = planList.size();
		return countFromBanner;
	}

//	public void validateResult()
//	{
//		String resultInString = resultText.getText().substring(0,2);
//		int resultInNumber = Integer.parseInt(resultInString);
//		int planListNumber = planList.size();
//		System.out.println("Total of Result number is "+resultInNumber);
//		System.out.println("Total Plan list number is "+planListNumber);
//		
//		if(resultInNumber==planListNumber)
//		{
//			System.out.println("Results are matching - TC is Passed");
//		}
//		else
//		{
//			System.out.println("Results are not matching - TC is Failed");
//		}
//		
	
}
