package coverFoxTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import coverFoxBase.A_Base;
import coverFoxUtility.Utility_CommonlyUsedMethods;
import coverFoxPOM.A_CoverFoxHomePage;
import coverFoxPOM.B_CoverFoxHealthPlanPage;
import coverFoxPOM.C_CoverFoxMemberDetailsPage;
import coverFoxPOM.D_CoverFoxAddressDetailsPage;
import coverFoxPOM.E_CoverFoxResultPage;

public class TC1234_CoverFox_ValidateBannerCount extends A_Base
		/*It�s mandatory to extend the Base class in the Test class because we need access to the driver.
 		This ensures that all test classes can utilize the same instance of the driver for browser interactions.*/
{
	/* 1]Open Browser/Open An Application - @BeforeClass
       2]Gender Selection, Next Click, Age Selection, Pincode and MobNo,Click on Next - @BeforeMethod
	   3]Logout from Application - @AfterMethod
	   4]Close Browser/Close An Application - @AfterClass */
	
	/*We declare all of this globally to ensure it's accessible wherever needed throughout the project. */
	A_CoverFoxHomePage homePage; //UserDefinedDataType variableName;
	B_CoverFoxHealthPlanPage healthPlanPage;
	C_CoverFoxMemberDetailsPage memberDetailsPage;
	D_CoverFoxAddressDetailsPage addressDetailsPage;
	E_CoverFoxResultPage resultPage;
	
	String excelPath=System.getProperty("user.dir")+"\\DataSheets\\ExcelDataReading.xlsx";
	String sheetName="Sheet9";//from DataSheets Folder
	
	public static Logger logger;//variable declaration - dataType of variable is Logger and variable is logger.
		
	
		// --> 1] Open Browser/Open An Application - @BeforeClass -
	@BeforeClass
	public void openApplication() throws IOException
	{
		launchBorwser();//this is an Base class method and we extends here so we can directly call here
		
		// for log4j -->
		logger=Logger.getLogger("8th_June_CoverFoxLogger");//here we give a name to Logger
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Opening Application -");//we are giving information by writting this
	}
	
		// --> 2]Gender Selection, Next Click, Age Selection, Pincode and MobNo,Click on Next - @BeforeMethod -
	@BeforeMethod
	public void enterAllDetails() throws EncryptedDocumentException, IOException, InterruptedException
	{
		/*To call methods of all POM classes, we need to create object - 
		  but here we declared it globally, by declaring them globally, we can initialize and access 
		  them more conveniently,so allowing us to use them directly without repeated object creation */
		
		homePage = new A_CoverFoxHomePage(driver);//Initialization of the mtd of POM Class need to give driver reference
		homePage.clickOnSelectedGenderButton();//Calling the methods of POM Class 
		logger.info("Clicking on Gender Button -");
		Thread.sleep(1000);
		
		healthPlanPage = new B_CoverFoxHealthPlanPage(driver);
		healthPlanPage.clickOnNextButton();
		logger.info("Clicking on Next Button -");
		Thread.sleep(1000);
		
		memberDetailsPage = new C_CoverFoxMemberDetailsPage(driver);
		memberDetailsPage.handleAgeDropDown(Utility_CommonlyUsedMethods.readDataFromExcel(excelPath, sheetName, 1, 0));
		//we are fetching data from excel sheet whenever needed them and it's present in Utility class
		logger.info("Handling Age DropDown -");
		memberDetailsPage.clickOnNextButton();
		logger.info("Clicking on Next Button -");
		Thread.sleep(1000);
		
		addressDetailsPage = new D_CoverFoxAddressDetailsPage(driver);
		logger.warn("Enter Valid Pincode - ");
		addressDetailsPage.enterPincode(Utility_CommonlyUsedMethods.readDataFromExcel(excelPath, sheetName, 1, 1));
		logger.info("Entering Pincode -");
		logger.warn("Enter Valid Mobile Number - ");
		addressDetailsPage.enterMobno(Utility_CommonlyUsedMethods.readDataFromExcel(excelPath, sheetName, 1, 2));
		logger.info("Entering Mobile Number -");
		addressDetailsPage.clickOnContinueButton();
		logger.info("Clicking on Continue Button -");
		logger.error("Please Check Details Again- ");
		Thread.sleep(2000);
	}
	
	//Exact validation or Actual Testing is performed within the @Test method. We can perform multiple @Test 
  @Test
  public void validatePolicyCount() 
  {
	  resultPage = new E_CoverFoxResultPage(driver);
	  int textCount = resultPage.getCountFromText();
	  int bannerCount = resultPage.getCountFromBanner();
	  logger.info("Validating Results -");
	  //Assert.fail();
	  Assert.assertEquals(textCount, bannerCount,"text count not matching with banner count, TC Failed");
	  //Assert.fail();//giving this for taking screenshot on failed TC - Listener Class
      Reporter.log("saurabh has made changes in code", true);
      Reporter.log("Snehal Made Chnages", true);
  }
  
  	// --> 3]Logout from Application - @AfterMethod
  
  	// --> 4]Close Browser/Close An Application - @AfterClass 
  @AfterClass
  public void closeApplication()
  {
	  logger.info("Closing Browser -");
	  closeBrowser();
  }
  
}
