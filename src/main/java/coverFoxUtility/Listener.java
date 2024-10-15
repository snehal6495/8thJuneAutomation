package coverFoxUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import coverFoxBase.A_Base;

public class Listener extends A_Base implements ITestListener
{
	@Override
	public void onTestSuccess(ITestResult result)
	{
		Reporter.log("TC"+result.getName()+"Passed",true);
	}

	@Override
	public void onTestFailure(ITestResult result)
						/*Use Assert.fail() in the Test class to trigger a screenshot capture. 
						This will take a screenshot after the TC fails.*/
	{
		try 
		{
			Reporter.log("Taking Screenshots of Failed TC..",true);
			Utility_CommonlyUsedMethods.takeScreenShot(driver, result.getName());
			/* - takeScreenShot - this method is from the utility class.
			   - driver - We need the driver for browser interactions, which is why we extend the Base class, 
					   as the driver instance is declared in the Base class.*/ 
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
