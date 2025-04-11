package PomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import UtilClasses.Utility1;

public class HomePage extends Utility1{

	WebDriver driver;

	@FindBy(xpath="//div[@class='_28p97w']")
	private WebElement ProfileName;

	@FindBy(xpath="(//li[@class='_2NOVgj'])[10]")
	private WebElement logoutText;

	@FindBy(xpath="//div[text()='My Profile']")
	private WebElement myProfile;

	@FindBy(xpath="//div[text()='Dinesh']")
	private WebElement name;

	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
		this.driver=driver;
	}

	public void HoverToProfile() throws InterruptedException
	{
		Thread.sleep(2000);
		explicitWait(driver,ProfileName);
		movetoElement(driver, ProfileName); 
	}

	public String getLogoutText()
	{
		return logoutText.getText();
	}

	public void clickMyProfile() 
	{

		myProfile.click();
		moveByOffset(driver);
	}
	public void isDisplayed()
	{

		if(name.isDisplayed())
		{
			System.out.println("Logged in");
		}
		else
		{
			Assert.fail();
		}
	}
}
