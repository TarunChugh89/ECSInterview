package page;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BasePage;
import utilities.HandleException;
import utilities.LocalizationUtil;

public class ProductDescription extends BasePage {
	
	By qtylocator= By.xpath("//input[@name=\"quantity\"]");
	By addToCartButton= By.xpath("//button[@type=\"submit\"]");
	String filePath="./src/test/resources/LocatorData/ProductDescription.json";
	By bookYourTime= By.xpath("//span[text()=\"Book Your Time\"]/..");
	By bookedTime= By.xpath("//span[@class=\"bookeasy-button-text\"]");
	By CartPageTitle= By.xpath("//h1[text()=\"Your cart\"]");
	Map<String,String> locatorData= new HashMap<>();
	

	public ProductDescription(WebDriver driver, ExtentTest test,String language) {
		super(driver, test);
		LocalizationUtil.loadLocators(filePath, test);
		this.locatorData=LocalizationUtil.getLocators(language,test);
	}

	
	public void selectQty(int qty)
	{
		click(qtylocator, "Qty Selection");
		try {
		inputText(qtylocator, String.valueOf(qty), "Insert qty of items to be added");
		}
		catch(NumberFormatException e)
		{
			HandleException.handleException("Input Text", "Qty Locatory", e, true, null, test);
		}
	}
	
	public void addToCart(int qty, boolean appointment)
	{
		selectQty(qty);
		if(appointment)
		{
			booktime();
		}
		click(addToCartButton, "AddtoCart");
		waitForWebElementPresence(CartPageTitle, "CartDasBoard Dispalyed");
		
	}
	
	public void booktime()
	{
		click(bookYourTime, "Book Your Time");
		CalendarPage cal= new CalendarPage(driver, test);
		cal.selectDataFromCalendar();
	}
	
	public String getSelectedSlotTime()
	{
		return getAttribute(bookedTime, "class", "BookedTime");
	}

	
	
}
