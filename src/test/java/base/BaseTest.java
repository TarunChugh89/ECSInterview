package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

import factory.BrowserFactory;
import factory.DriverFactory;
import factory.ExtentFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import listener.ExtentReportListener;
import utilities.DatabaseOperation;
import utilities.PropertiesOperation;

@Listeners(ExtentReportListener.class)
public class BaseTest {
	
	BrowserFactory bwf;
	ThreadLocal<ExtentTest> test= new ThreadLocal<ExtentTest>();
	ThreadLocal<SoftAssert> softAssert= new ThreadLocal<SoftAssert>();
	
	public void setAssert(SoftAssert param)
	{
		softAssert.set(param);
	}
	
	public SoftAssert getAssert()
	{
		return softAssert.get();
	}
	
	public void setTestStep(String testDescription)
	{
		test.set(report().createNode(testDescription));
	}
	
	public ExtentTest testStep()
	{
		return test.get();
	}
	
	
	@BeforeMethod
	public void testMethodSetup()
	{
		bwf= new BrowserFactory();
		setAssert(new SoftAssert());
		DriverFactory.getInstance().setDriverFactory(bwf.init_Driver(PropertiesOperation.init_Property().getProperty("browser")));
		
		
	}
	
	@AfterMethod
	public void testMethodTearDown()
	{
		DriverContext().quit();
	}
	
	public WebDriver DriverContext()
	{
		return DriverFactory.getInstance().getDriverFactory();
	}
	
	public ExtentTest report() 
	{
		return ExtentFactory.getInstance().getExtentFactory();
	}

}
