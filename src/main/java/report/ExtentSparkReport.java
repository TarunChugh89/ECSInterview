package report;

import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentSparkReport {
	
	public static String filePath=System.getProperty("user.dir")+"/build/TestAutomationReport.html";
	
	public static ExtentReports createExtentReport(ITestContext context)
	{
		try {
		ExtentSparkReporter reporter= new ExtentSparkReporter(filePath);
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setReportName("Automation Test Report");
		ExtentReports reports=new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("Test Suite Name", context.getName());
		return reports;
		}
		catch(Exception e)
		{
			
			System.err.println("Failed to create Extent Report"+ e.getMessage());
			throw new RuntimeException("Failed to initialize Report");
		}
		
	}

}
