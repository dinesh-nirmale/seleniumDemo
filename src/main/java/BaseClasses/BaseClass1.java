package BaseClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import UtilClasses.Utility1;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass1 extends Utility1{


	public static WebDriver getWebDriver(String a)
	{
		
		if(a.equals("chrome"))
		{
//			WebDriverManager.chromedriver().setup();
			
			WebDriverManager.chromedriver().driverVersion("134.0.6998.179").setup();
//			System.setProperty("webdriver.chrome.driver","src\\main\\resources\\Browsers\\chromedriver.exe");
//	        WebDriverManager.chromedriver().driverVersion("134.0.6998.179").clearResolutionCache().forceDownload().setup();

			ChromeOptions options = new ChromeOptions();
	        options.addArguments("--remote-allow-origins=*"); // Important for recent Selenium versions
	        options.addArguments("--allowed-ips="); // This is what fixes the 403

	        WebDriver driver = new ChromeDriver(options);
//			WebDriver driver=new ChromeDriver();
			driver.get("https:\\www.flipkart.com");
			driver.manage().window().maximize();
			return driver;

		}

		else if(a.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","src\\main\\resources\\Browsers\\chromedriver.exe");
			WebDriver driver=new FirefoxDriver();
			driver.get("https://www.flipkart.com");
			driver.manage().window().maximize();
			return driver;
		}

		else 
		{
			System.setProperty("webdriver.ie.driver","C:\\Program Files\\geckodriver.exe");
			WebDriver driver=new ChromeDriver();
			driver.get("https://www.flipkart.com");
			driver.manage().window().maximize();
			return driver;
		}

	}
}
