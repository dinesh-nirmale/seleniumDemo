package UtilClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility1 {


	public static void movetoElement(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).pause(2000).build().perform();;	
	}

	public static void implicitWait(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static boolean explicitWait(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,10);
		return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();

	}

	public static void moveByOffset(WebDriver driver)
	{
		Actions act=new Actions(driver);
		act.moveByOffset(200, 0).release().build().perform();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}


	public static List<String> getDataFromExcel(int firstRow,int lastRow) throws EncryptedDocumentException, IOException
	{
		FileInputStream file=new FileInputStream("C:\\Users\\Dinesh\\OneDrive\\Documents\\QA\\Book2.xlsx");

		List<String> list=new ArrayList<>();

		Sheet sheet=WorkbookFactory.create(file).getSheet("Sheet1");

		for(int i=firstRow;i<=lastRow;i++)
		{
			try {
				String stringdata=sheet.getRow(i).getCell(1).getStringCellValue();
				list.add(stringdata);
			}catch(Exception e)
			{
				long intdata=(long) sheet.getRow(i).getCell(1).getNumericCellValue();
				String string=String.valueOf(intdata);
				list.add(string);
			}

		}

		return list;	
	}

	public String takeScreenshot(WebDriver driver) throws IOException
	{

		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-hh-mm-ss");
		
		String str=sdf.format(new Date());
		
		File source=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		File destiny=new File("screenShot "+str+".png");
		
		String path=destiny.getAbsolutePath();

		FileUtils.copyFile(source, destiny);
		
		return path;

	}
	
	public String getConfigData(String key) throws IOException
	{
		FileInputStream file=new FileInputStream("configuration\\config.properties");
		
		Properties prop=new Properties();
		
		prop.load(file);
		 
		return prop.getProperty(key);
	
	}

}
