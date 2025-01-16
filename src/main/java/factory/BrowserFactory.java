package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	
	WebDriver driver;
	
	public WebDriver init_Driver(String browser)
	{
		 if (browser == null || browser.isEmpty()) {
		        throw new IllegalArgumentException("Browser name cannot be null or empty. Please provide a valid browser.");
		    }
		
		switch(browser.toLowerCase())
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			break;
		default:
			throw new IllegalArgumentException("Invalid browser is selected, please select the correct one");
		}
		
		return driver;
	}

}
