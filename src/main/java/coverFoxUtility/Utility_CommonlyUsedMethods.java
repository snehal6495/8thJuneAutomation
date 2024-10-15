package coverFoxUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility_CommonlyUsedMethods //All Commonly Used Methods are present here in Utility Class -
{
	//1] Screenshot - create folder 
		public static void takeScreenShot(WebDriver driver, String fileName) throws IOException 
		{
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String timeStamp = new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
			
			//File dest= new File("E:\\Automation VT\\Java Automation Program\\A1_Automation_SeleniumProject\\screenShot"+fileName+timeStamp+".png");
			
			File dest = new File(System.getProperty("user.dir") +"\\screenShot\\"+fileName+timeStamp+".png");
			FileHandler.copy(source, dest);
		}
			
		//2] ExcelDataReading -	create folder and dragAndDrop excelFile or copy excelFile
		/*do not directly do changes (edit/delete) in excelSheet in eclipse folder
		  - Rather open in location - 
			go to created folder DataSheets --> Right click on excel file --> click on properties --> click on arrow and open folder 
		
		- Any data we want to save in excel then save as a String although it is a number 
		- How to convert Integer Data in String in Excel ? --> simply put single quote in front of Integer Data(') 
			--> '28 or '12345 and do enter it shows green coloured mark so it is String data now
		*/
		public static String readDataFromExcel(String excelPath, String sheetName, int rowNum, int cellNum) throws EncryptedDocumentException, IOException 
		{
			FileInputStream myFile = new FileInputStream(excelPath);
			String value = WorkbookFactory.create(myFile).getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
			return value;//return all data which are read in string fmt.  
			/* so here return type of readDataFromExcel() mtd is String instead void bcz our data that is stored in String 
			format as value variable and gives in return form as string value.*/
		}
		
		//3] Read Data from Property File - config.properties (name only config.properties file)
		/*For create Property file - Right click on Project Name--> new --> file*/
		public static String readDataFromPropertyFile(String key) throws IOException
		{
			Properties properties = new Properties();
			FileInputStream myFile = new FileInputStream(System.getProperty("user.dir")+"//config.properties");
			properties.load(myFile);//for read the data we should load the file
			String value = properties.getProperty(key);
			return value;//we need to return the data that was read
			//the mtd returns a string value and also - return value; gives string value 
			//so our method need to change from void to String
			
			
		}


}
