package coverFoxBase;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import coverFoxUtility.Utility_CommonlyUsedMethods;



public class A_Base //base class name should be Base only and this is normal Java Class
{
	 /*	Things we need to do in Base Class
	    driver
	 	url
	 	launch browser
	 	close browser */
	
	//1] Driver Initialization -
	protected static WebDriver driver;
	/*  1] Global Declaration: The driver is declared Globally to ensure it can be used across different classes and packages, such as 
         Page Object Model (POM) classes, test classes, for taking screenshots, or in listeners.
         This is essential for consistent browser interaction throughout the project.
 	    2] Static Modifier: It is declared as Static to prevent the creation of multiple instances. This helps avoid 
         NullPointerExceptions that can occur if different parts of the code try to reference a 
         non-existent instance.
        3] Protected Access: The Protected access modifier allows the driver to be accessed by any subclass of the base class. 
         This enables re-usability and encapsulation, allowing subclasses to extend the base class without 
         needing to redefine the driver.
         If driver not declared as protected then its value is default and default access is only within the 
         same pkg so not able to share the driver across different parts of the project.*/
	
	//2] Launch Browser -
	public void launchBorwser() throws IOException
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");//for disable notifications if any
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		driver.get(Utility_CommonlyUsedMethods.readDataFromPropertyFile("url"));
		//bcz we stored url in property file - config.properties - className.mtdName();
		
		Reporter.log("launching browser", true);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
		Reporter.log("waiting..", true);
	}
	
	//3] Close Browser -
	public void closeBrowser()
	{
		Reporter.log("closing browser", true);
		driver.quit();
	}

}
