package base;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import utilities.HandleException;

public class BasePage {
	
	protected WebDriver driver;
	protected ExtentTest test;
	public final static int timeOut=10; 
	
	public BasePage(WebDriver driver,ExtentTest test)
	{
		this.driver=driver;
		this.test=test;
	}
	
	public void click(By locator, String locatorValue)
	{
		int attempt=1;
		while(attempt<3) {
		try
		{
			WebDriverWait wt= new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wt.until(ExpectedConditions.elementToBeClickable(locator)).click();
			String message=String.format("[%s] operation is successfully performed at [%s]", "Click",locatorValue);
			test.info(message);
			return;
			
		}
		catch(StaleElementReferenceException| ElementClickInterceptedException e)
		{
			String message= String.format("[%s] operation will be retried again for the [%d]th time due to [%s]", "click",attempt+1,e.getClass().getSimpleName());
			test.info(message);
			attempt++;
		}
		catch(Exception e)
		{
			HandleException.handleException("click", locatorValue, e, true,null,test);
			return;
		}
		}
		
		HandleException.handleException("click", locatorValue, new Exception("Max retry attempts exceeded"), true,null,test);
	}
	
	public void FluentClick(By locator, String locatorValue)
	{
		try
		{
			FluentWait<WebDriver> wait= new FluentWait<WebDriver>(driver)
										.pollingEvery(Duration.ofMillis(500))
										.withTimeout(Duration.ofSeconds(timeOut))
										.ignoring(StaleElementReferenceException.class)
										.ignoring(ElementNotInteractableException.class)
										.ignoring(ElementClickInterceptedException.class);
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
			String message=String.format("[%s] operation is successfully performed at [%s]", "Click",locatorValue);
			test.info(message);
										
		}
		catch(Exception e)
		{
			HandleException.handleException("click", locatorValue, e, true,null,test);
		}
	}
	
	
	public void inputText(By locator, String value, String locatorValue)
	{
		
		try {
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			WebElement ele=wt.until(ExpectedConditions.elementToBeClickable(locator));
			ele.clear();
			ele.sendKeys(value);
			String message=String.format("[%s] operation is successfully performed at [%s] with value inserted as [%s]", "Input Text",locatorValue,value);
			test.info(message);
			
		}
		catch(Exception e)
		{
			HandleException.handleException("InputText", locatorValue, e, true,null,test);
		}
		
	}
	
	public void inputKeys(By locator, Keys Actions, String locatorValue)
	{
		
		try {
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			WebElement ele=wt.until(ExpectedConditions.visibilityOfElementLocated(locator));
			ele.sendKeys(Actions);
			String message=String.format("[%s] operation is successfully performed at [%s] with value inserted as [%s]", "Input Keys",locatorValue,Actions.toString());
			test.info(message);
			
		}
		catch(Exception e)
		{
			HandleException.handleException("InputKeys", locatorValue, e, true,null,test);
		}
		
	}
	
	public String getText(By locator, String locatorValue)
	{
		
		try {
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			return wt.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
			
		}
		catch(Exception e)
		{
			 return HandleException.handleException("GetText", locatorValue, e, false,"",test);
		}
		
		
	}
	
	public String getAttribute(By locator, String attribute, String locatorValue)
	{
		
		try {
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			return wt.until(ExpectedConditions.visibilityOfElementLocated(locator)).getAttribute(attribute);
			
		}
		catch(Exception e)
		{
			return HandleException.handleException("GetAttribute", locatorValue, e, false,"",test);
		}
		
	}
	
	public WebElement getElement(By locator,String locatorValue)
	{
		try {
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			return wt.until(ExpectedConditions.visibilityOfElementLocated(locator));
			
		}
		catch(Exception e)
		{
			return HandleException.handleException("GetElement", locatorValue, e, true,null,test);
		}
	}
	
	public WebElement waitForWebElementPresence(By locator,String locatorValue)
	{
		try {
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			return wt.until(ExpectedConditions.visibilityOfElementLocated(locator));
			
		}
		catch(Exception e)
		{
			return HandleException.handleException("WaitforWebElementPresence", locatorValue, e, true,null,test);
		}
	}
	
	public boolean isEnabled(By locator,String locatorValue)
	{
		try {
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			return wt.until(ExpectedConditions.visibilityOfElementLocated(locator)).isEnabled();
			
		}
		catch(Exception e)
		{
			return HandleException.handleException("isEnabled", locatorValue, e, false,false,test);
		}
	}
	
	public boolean isDisplayed(By locator,String locatorValue)
	{
		try {
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			return wt.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
			
		}
		catch(Exception e)
		{
			return HandleException.handleException("isDisplayed", locatorValue, e, false,false,test);
		}
	}
	
	public boolean isSelected(By locator,String locatorValue)
	{
		try {
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			return wt.until(ExpectedConditions.visibilityOfElementLocated(locator)).isSelected();
			
		}
		catch(Exception e)
		{
			return HandleException.handleException("isEnabled", locatorValue, e, false,false,test);
		}
	}
	
	public String getTagName(By locator,String locatorValue)
	{
		try {
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			return wt.until(ExpectedConditions.visibilityOfElementLocated(locator)).getTagName();
			
		}
		catch(Exception e)
		{
			return HandleException.handleException("GetTagName", locatorValue, e, false,"",test);
		}
	}
	
	//We should stick to single Responsibility principle considering that is industry standard. For below we have use
	//fluent wait to just make sure exceptions are handled correctly
	public void performByAction(String action, By locator, String locatorValue)
	{
		try
		{
			FluentWait<WebDriver> wt= new FluentWait<WebDriver>(driver)
											.pollingEvery(Duration.ofMillis(500))
											.withTimeout(Duration.ofSeconds(timeOut))
											.ignoring(StaleElementReferenceException.class)
											.ignoring(ElementNotInteractableException.class)
											.ignoring(ElementClickInterceptedException.class);
											
			wt.until(ExpectedConditions.visibilityOfElementLocated(locator));
			Actions act= new Actions(driver);
			
			switch(action.toLowerCase())
			{
			case "click":
				act.click(getElement(locator, locatorValue)).perform();
				break;
			case "doubleclick":
				act.doubleClick(getElement(locator, locatorValue)).perform();
				break;
			case "hover":
				act.moveToElement(getElement(locator, locatorValue)).perform();
				break;
			case "rightclick":
				act.contextClick(getElement(locator, locatorValue)).perform();
				break;
			default:
				String errorMessage= String.format("Failed to perform operation [%s] due to unsupported Action type", action);
				throw new IllegalArgumentException(errorMessage);
				
			}
			String successMessage=String.format("[%s] operation is successfully performed at [%s]", action,locatorValue);
			test.info(successMessage);
			
			
		}
		catch(Exception e)
		{
			 HandleException.handleException(action, locatorValue, e, true,null,test);
		}
	}
	
	// since you are throwing illegal argument exception and it is being catch by catch block to fail it accordingly.
	public void selectFromList(String action,By locator,String value, String locatorValue)
	{
		if(!isEnabled(locator, locatorValue))
		{
			String errorMessage= String.format("List at give value [%s] is not enabled for further selection", locatorValue);
			throw new IllegalStateException(errorMessage);
		}
		WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		WebElement webElmt=wt.until(ExpectedConditions.visibilityOfElementLocated(locator));
		Select sel= new Select(webElmt);
		
		try {
		switch(action.toLowerCase())
		{
		case "index":
			int index= Integer.parseInt(value);
			sel.selectByIndex(index);
			break;
		case "value":
			sel.selectByValue("value");
			break;
		case "text":
			sel.selectByVisibleText(value);
			break;
			default:
				String errorMessage= String.format("Failed to perform Select by [%s] due to unsupported type", action);
				throw new IllegalArgumentException(errorMessage);
		
		}
		String successMessage=String.format("Select by [%s] operation is successfully performed at [%s]", action,locatorValue);
		test.info(successMessage);
		}
		catch(Exception e)
		{
			 HandleException.handleException("SelectBy"+action, locatorValue, e, true,null,test);
		}
	}
	
	public void selectFromListByCollection(String action,By locator,String value, String locatorValue)
	{
		try {
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			List<WebElement> options= wt.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			
			boolean flag=false;
			switch(action.toLowerCase())
			{
			case "value":
				for(WebElement e: options)
				{
					//locator value is wrong here for time being using
					if(e.getAttribute(locatorValue).trim().equals(value))
					{
						
						e.click();
						flag=true;
						break;
					}
				}
				break;
			case "index":
				int index= Integer.parseInt(value);
				if(index<options.size())
				{
					options.get(index).click();
					flag=true;
				}
				else
				{
					throw new IllegalArgumentException("Index size for Selected operation is not present in the given list at"+locatorValue);
				}
				break;
			case "text":
				for(WebElement e: options)
				{
					System.out.println(e.getText().trim());
					if(e.getText().trim().equals(value))
					{
						e.click();
						flag=true;
						break;
					}
				}
				break;
			default:
				String errorMessage= String.format("Failed to perform Select by [%s] due to unsupported type", action);
				throw new IllegalArgumentException(errorMessage);
			}
			
			if(!flag)
					
			{
				String errorMessage=String.format("No element found in the list with given criteria at [%s]", locatorValue);
				throw new Exception(errorMessage);
			}
			else
			{
				test.info(String.format("Element with %s = [%s] selected at [%s]", action, value, locatorValue));
			}
			
			
		}
		catch(Exception e)
		{
			HandleException.handleException("SelectBy"+action, locatorValue, e, true,null,test);
		}
	}
	
	public void alertAction(String action)
	{
		try
		{
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			switch(action.toLowerCase())
			{
			case "accept":
				wt.until(ExpectedConditions.alertIsPresent()).accept();
				break;
			case "decline":
				wt.until(ExpectedConditions.alertIsPresent()).dismiss();
				break;
				default:
					throw new IllegalArgumentException("Unsupported Selection for Alert is:" + action);
			}
			test.info(String.format("Alert [%s] operation is performed", action));
		}
		catch(Exception e)
		{
			HandleException.handleException(action+"on Alert", "for Alert", e, true,null,test);
		}
	}
	
	public String getAlertText() {
	    try {
	        WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	        String alertText = wt.until(ExpectedConditions.alertIsPresent()).getText();

	        test.info("Alert text retrieved: " + alertText);
	        return alertText;

	    } catch (Exception e) {
	    	return HandleException.handleException("Get Text", "for Alert", e, false,"",test);
	    }
	}
	
	public List<String> getTextOptions(By locator, String locatorValue)
	{
		List<String> data= new ArrayList<String>();
		 try {
		        WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		        List<WebElement> options= wt.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		        
		        for(WebElement e: options)
		        {
		        	data.add(e.getText());
		        }
		        
		    } catch (Exception e) {
		    	 HandleException.handleException("GetTextOptions", locatorValue, e, false,"",test);
		    }
		 return data;
	}
	
	

}
