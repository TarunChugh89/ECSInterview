package factory;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
	
	//The static variable and methods in DriverFactory ensure global access and consistency, while the ThreadLocal ensures thread safety and isolation. 
	//This design combines the benefits of singleton pattern and thread-local storage to manage WebDriver instances efficiently.
	
	//Thread local make sure that for each thread we are having seperate instance of webdriver,
	//while static make sure, we have one object which Driverfactory reference by instance loaded into memory once and each test will have 
	//same entry point via that.
	
	ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>();
	
	private DriverFactory()
	{
		
	}
	
	public static DriverFactory instance= new DriverFactory();
	
	public static DriverFactory getInstance()
	{
		return instance;
	}
	
	
	public void setDriverFactory(WebDriver param)
	{
		driver.set(param);
	}
	
	public WebDriver getDriverFactory()
	{
		return driver.get();
	}
	
	

}
