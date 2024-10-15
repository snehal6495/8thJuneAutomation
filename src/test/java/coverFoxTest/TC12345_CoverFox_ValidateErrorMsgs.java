package coverFoxTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import coverFoxBase.A_Base;
import coverFoxPOM.A_CoverFoxHomePage;
import coverFoxPOM.B_CoverFoxHealthPlanPage;
import coverFoxPOM.C_CoverFoxMemberDetailsPage;
import coverFoxPOM.D_CoverFoxAddressDetailsPage;
import coverFoxPOM.E_CoverFoxResultPage;
import coverFoxUtility.Utility_CommonlyUsedMethods;

public class TC12345_CoverFox_ValidateErrorMsgs extends A_Base
{
	A_CoverFoxHomePage homePage; //UserDefinedDataType variableName;
	B_CoverFoxHealthPlanPage healthPlanPage;
	C_CoverFoxMemberDetailsPage memberDetailsPage;
	D_CoverFoxAddressDetailsPage addressDetailsPage;
	E_CoverFoxResultPage resultPage;
	
	String excelPath=System.getProperty("user.dir")+"\\DataSheets\\ExcelDataReading.xlsx";
	String sheetName="Sheet9";//from DataSheets Folder
	
	public static Logger logger;//variable declaration - dataType of variable is Logger and variable is logger.
	
	// --> 1] Open Browser/Open An Application - @BeforeClass -
	
	// --> 2]Gender Selection, Next Click, Age Selection, Pincode and MobNo,Click on Next - @BeforeMethod -
	@BeforeMethod
	public void enterAllDetails() throws EncryptedDocumentException, IOException, InterruptedException
	{
			/*To call methods of all POM classes, we need to create object - 
			  but here we declared it globally, by declaring them globally, we can initialize and access 
			  them more conveniently,so allowing us to use them directly without repeated object creation */
		launchBorwser();
		logger=Logger.getLogger("8th_June_CoverFox");
		PropertyConfigurator.configure("log4j.properties");
		logger.info("opening application");
		
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
		Thread.sleep(1000);	 
	}
	
  @Test(priority = -1)
  public void validatePincodeErrorMsg() throws EncryptedDocumentException, IOException 
  {
	  logger.warn("Enter Valid Mobile Number - ");
	  addressDetailsPage.enterMobno(Utility_CommonlyUsedMethods.readDataFromExcel(excelPath, sheetName, 1, 2));
	  logger.info("Entering Mobile Number -");
	  addressDetailsPage.clickOnContinueButton();
	  logger.info("Clicking on Continue Button -");
	  logger.error("Please Check Details Again- ");
	  
	  String actualPinErrorMsg= addressDetailsPage.getPinErrorMsg();
	  String expectedPinErrorMsg = Utility_CommonlyUsedMethods.readDataFromExcel(excelPath, sheetName, 1, 3);
	  
	  Assert.assertEquals(actualPinErrorMsg, expectedPinErrorMsg, "Error Messages Not Matching, TC Failed");
  }
  
  @Test
  public void validateMobilenoErrorMsg() throws EncryptedDocumentException, IOException
  {
	  logger.warn("Enter Valid Pincode - ");
	  addressDetailsPage.enterPincode(Utility_CommonlyUsedMethods.readDataFromExcel(excelPath, sheetName, 1, 1));
	  logger.info("Entering Pincode -");
	  logger.info("Clicking on Continue Button -");
	  logger.error("Please Check Details Again- ");
	  
	  String actualMobErrorMsg= addressDetailsPage.getMobnoErrorMsg();
	  String expectedMobErrorMsg = Utility_CommonlyUsedMethods.readDataFromExcel(excelPath, sheetName, 1, 4);
	  
	  Assert.assertEquals(actualMobErrorMsg, expectedMobErrorMsg, "Error Messages Not Matching, TC Failed");
  }
  
  	//--> 3]Logout from Application - @AfterMethod
  
 	// --> 4]Close Browser/Close An Application - @AfterClass 
  	@AfterMethod
  	public void closeApplication()
  	{
  		logger.info("Closing Browser -");
	    closeBrowser();
  	}
 
}
