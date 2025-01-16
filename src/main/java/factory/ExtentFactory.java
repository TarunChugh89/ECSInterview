package factory;

import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {
	
	ThreadLocal<ExtentTest> test= new ThreadLocal<ExtentTest>();
	
	private ExtentFactory()
	{
		
	}
	
	public static ExtentFactory instance= new ExtentFactory();
	
	public static ExtentFactory getInstance()
	{
		return instance;
	}
	
	public void setExtentFactory(ExtentTest param)
	{
		test.set(param);
		
	}
	
	public ExtentTest getExtentFactory()
	{
		return test.get();
	}
	

}
