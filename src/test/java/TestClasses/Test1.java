package TestClasses;

import java.io.IOException;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


import BaseClasses.BaseClass1;
import PomClasses.HomePage;
import PomClasses.LoginPage;
import PomClasses.ProfilePage;

import UtilClasses.Utility1;

public class Test1 {

	WebDriver driver;
	LoginPage lp;
	HomePage hp;
	ProfilePage pp;
	
	ExtentReports reports;
	ExtentTest test;
	
	@BeforeClass
	@Parameters({"browser"})
	public void beforeClass(@Optional("chrome") String a)
	{
		reports=new ExtentReports("ExtentReports.html",true);
		test=reports.startTest("AddNewAddress");
		
		driver=BaseClass1.getWebDriver(a);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		lp=new LoginPage(driver);
		hp=new HomePage(driver);
		pp=new ProfilePage(driver);
		
	}
	
	@Test 
    public void VerifyUserCanLogin() throws InterruptedException, IOException 
	{
		lp.enterUserName();
		lp.enterPassword();
		lp.enterSubmitButton();
		hp.HoverToProfile();
		
		String text=hp.getLogoutText();
		Assert.assertEquals(text, "Logout");
		
		test.log(LogStatus.PASS, "LogIn test passed");
		
    }
	
	@DataProvider(name="dataSet")
	public String[][] dataToTest()
	{
		String[][] data= {{"Dinesh","9011159878","416108","Takliwadi"},
				{"Ganesh","7057222089","416107","Takliwadi"}};  
		return data;
	}
	
	@Test (priority=1, dataProvider="dataSet")
	public void verifyUserCanAddNewAddress(String a,String b,String c,String d) throws InterruptedException, EncryptedDocumentException, IOException
	{
		hp.HoverToProfile();
		hp.clickMyProfile();
		pp.clickManageAddress();
		
		pp.clickAddNewAddress();
		
		Thread.sleep(1000);
		int beforecount=pp.getCount();
		
		List<String> datalist=new ArrayList<>();
		datalist.add(a);
		datalist.add(b);
		datalist.add(c);
		datalist.add(d);
		
		pp.insertDataForAddress(datalist);
		pp.enterAddress();
		pp.SelectAddressType();	
		pp.clickSave();
		Thread.sleep(2000);
	//	Assert.fail();
		int aftercount=pp.getCount();
		Assert.assertEquals(aftercount, beforecount+1);
		
		test.log(LogStatus.PASS, "Added New Address");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(LogStatus.FAIL,test.addScreenCapture(pp.takeScreenshot(driver)),"Screenshot Taken");
		}
	}
	
	@AfterClass
	public void afterClass()
	{
		reports.endTest(test);
		reports.flush();
		driver.quit();
	}
		
}
