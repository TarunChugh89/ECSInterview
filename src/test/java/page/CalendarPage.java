package page;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import base.BasePage;
import utilities.HandleException;

public class CalendarPage extends BasePage {

	
	By datePicker= By.xpath("//div[@class=\"neo-date-table-bookeasy\"]//tr//td//span[@class=\"neo-day-bookeasy neo-day-enabled-bookeasy\"]");
	By nextMonthSelector= By.xpath("//span[@class=\"bookeasy-calendar-month-text\"]/..//span[@class=\"neo-paginate-bookeasy\"]");
	By slotConfirm= By.xpath("//span[contains(@class,\"neo-time-enabled-bookeasy\")]");
	By next= By.xpath("//button[@class=\"bookeasy-next-btn\"]");
	
	public CalendarPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
	}
	
	public void selectDataFromCalendar()
	{
		int attempt=0;
		while(attempt<2)
		{
		try
		{
			WebDriverWait wt= new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			try {
			List<WebElement> timeOptions= wt.until(ExpectedConditions.presenceOfAllElementsLocatedBy(datePicker));
			if(!timeOptions.isEmpty())
			{
				Random random= new Random();
				int index= random.nextInt(timeOptions.size());
				timeOptions.get(index).click();
				click(slotConfirm, "Confirmed Slot");
				click(next, "Next on Slot Confirm");
				return;
			}
			}
			catch(NoSuchElementException | TimeoutException e)
			{
				test.info("No date is availaible for current Month, Moving on to Next month");
			}
			
				List<WebElement> optionsAttempt= wt.until(ExpectedConditions.presenceOfAllElementsLocatedBy(nextMonthSelector));
				String temp="";
				if(!optionsAttempt.isEmpty())
				{
				int buttonindex= optionsAttempt.size()==1?1:2;
				temp= String.format("(//span[@class=\"bookeasy-calendar-month-text\"]/..//span[@class=\"neo-paginate-bookeasy\"])[%d]", buttonindex);
	        	click(By.xpath(temp), "Selecting Next Month");
				}
				else
				{
				throw new Exception("Next Month Button is not present on the calendar");
				}
				
		}
		catch(Exception e)
		{
			HandleException.handleException("Calendar Selection", "Calendar Button", e, true, null, test);
			return;
		}
		attempt++;
		}
		Assert.fail("Failed to select a date from the calendar after 2 attempts.");
	    test.fail("Failed to select a date from the calendar after 2 attempts.");
	}
	
	

}
