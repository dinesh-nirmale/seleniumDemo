package PomClasses;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilClasses.Utility1;

public class ProfilePage extends Utility1 {

	WebDriver driver;

	@FindBy(xpath="//div[text()='Manage Addresses']")
	private WebElement manageAddress;

	@FindBy(xpath="//div[text()='ADD A NEW ADDRESS']")
	private WebElement addNewAddress;

	@FindBy(xpath="//label[@for=\"HOME\"]")
	private WebElement addressType;

	@FindBy(xpath="//button[text()='Save']")
	private WebElement save;
	
	@FindBy(xpath="//div[@class='_26SF1Q']")
	private List<WebElement> getcount;

	@FindBy(xpath="//textarea[@class=\"_1sQQBU _1w3ZZo _1TO48q\"]")
	private WebElement EnterAddress;



	public  ProfilePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
		this.driver=driver;
	}

	public void clickManageAddress() throws InterruptedException
	{
        Thread.sleep(2000);
		manageAddress.click();
	}

	public void clickAddNewAddress()
	{
		explicitWait(driver,addNewAddress);
		addNewAddress.click();
	}

	public void insertDataForAddress(List<String> list) throws  EncryptedDocumentException, IOException
	{
		//List<String> al=getDataFromExcel(0,3);

		for(int i=1;i<=4;i++)
		{
			WebElement element=driver.findElement(By.xpath("((//form)[2]//input)["+i+"]"));
			element.sendKeys(list.get(i-1));
		}
		
	}

	public void enterAddress() throws InterruptedException
	{
		
		EnterAddress.sendKeys("abcdefg dffgdh dghheth fhrsdh");
		
		Thread.sleep(2000);
	}

	public void SelectAddressType()
	{
		addressType.click();
	}

	public void clickSave() throws IOException
	{
		//Utility1.takeScreenshot(driver);
		save.click();
	}
	
	public int getCount()
	{
		return getcount.size();	
	}
}
