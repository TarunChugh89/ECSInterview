package utilities;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

public class HandleException {
	
	public static <T> T handleException(String operation, String locatorValue, Exception e, boolean failTest, T defaultValue,ExtentTest test)
	{
		String errorMessage= String.format("The following operation [%s] is failed at [%s] because of the following exception [%s]",operation,locatorValue,e.getMessage());
		System.err.println(errorMessage);
		
		if(failTest)
		{
			test.fail(errorMessage);
			Assert.fail(errorMessage);
		}
		else
		{
			test.warning(errorMessage);
		}
		return defaultValue;
	}

}
