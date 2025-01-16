package listener;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.internal.IResultListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import factory.ExtentFactory;
import report.ExtentSparkReport;

public class ExtentReportListener implements IResultListener {
	
	static ExtentReports reports;
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		test=reports.createTest(result.getMethod().getMethodName());
		ExtentFactory.getInstance().setExtentFactory(test);
		System.out.println("The following test method has been started"+result.getMethod().getMethodName());
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentFactory.getInstance().getExtentFactory().pass("The following method is passed"+result.getMethod().getMethodName());
		System.out.println("Test Passed: " + result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentFactory.getInstance().getExtentFactory().fail("The following method is failed"+result.getMethod().getMethodName());
		System.out.println("Test Failed: " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentFactory.getInstance().getExtentFactory().skip("The following method is Skipped"+result.getMethod().getMethodName());
		System.out.println("Test Skipped: " + result.getMethod().getMethodName());
		
	}

	@Override
	public void onStart(ITestContext context) {
		reports=ExtentSparkReport.createExtentReport(context);
		System.out.println("Report Generation for Test Suite"+context.getName());
		
	}

	@Override
	public void onFinish(ITestContext context) {
		reports.flush();
		System.out.println("Report Completion for Test Suite"+context.getName());
		
		
	}
	
	

}
