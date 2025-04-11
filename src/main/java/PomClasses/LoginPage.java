package PomClasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilClasses.Utility1;

public class LoginPage extends Utility1 {

	WebDriver driver;
	
    @FindBy(xpath="(//input[@type='text'])[2]") private WebElement ID; 
	
	@FindBy(xpath="//input[@type='password']") private WebElement Pwd;
	
	@FindBy(xpath="(//button[@type=\"submit\"])[2]") private WebElement submit;
	
	
	public LoginPage(WebDriver driver)
	{
	   PageFactory.initElements(driver, this);
	   this.driver=driver;
	}
	
	public void enterUserName() throws IOException
	{
		//ID.sendKeys("9011159878");
		ID.sendKeys(getConfigData("email"));
		
	}
	
	public void enterPassword() throws IOException
	{
		//Pwd.sendKeys("dinu1426");
		Pwd.sendKeys(getConfigData("password"));
	}
	
	public void enterSubmitButton() throws InterruptedException
	{
		submit.click();
		Thread.sleep(2000);
	}
	
}
