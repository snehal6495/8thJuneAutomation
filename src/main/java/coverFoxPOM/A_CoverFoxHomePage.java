package coverFoxPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class A_CoverFoxHomePage 
{
	/* NOTES -->
	1] In POM classes, we don't need a main mtd -
	2] In a POM class, every variable should be declared as a private member
	3] POM classes strictly follows Encapsulation - all data present as private
		- data is encapsulated and not exposed publicly. 
		- declare variable as private and declare methods as public*/ 
	
	/* When designing POM, there are mainly 3 Sections - 
	 	1] Variable declaration --> webElements
	 	2] constructor --> to variable initialize
	 	3] method */
	
	//1] Variable declaration --> webElements
	//WebElement name=driver.findElement(By.x)
	@FindBy(xpath = "//div[text()='Female']") private WebElement gender;
	//private - The WebElement is declared private, meaning it can only be accessed within the class. 
	//WebElement - this is an DataType
	
	//2] Constructor --> to variable initialize
	public A_CoverFoxHomePage(WebDriver driver)//parameterized constructor - parameters are - WebDriver driver
	{
		PageFactory.initElements(driver, this);//also in the place of this we can write driver
		//initElements - initialize the driver and this means element
		//@FindBy(xpath = "//div[text()='Female']") private WebElement gender;
	}
	
	//3] Method --> to perform Actions on written variables 
	public void clickOnSelectedGenderButton()//non static method- 
	//call by creating object of that class where mtd is - using objectName.mtdName()
	{
		Reporter.log("Clicking on Gender Button after Gender Selection..", true);
		gender.click();
	}
	
} 
