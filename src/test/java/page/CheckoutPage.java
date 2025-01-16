package page;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;import com.google.protobuf.util.Durations;

import base.BasePage;
import utilities.HandleException;

public class CheckoutPage extends BasePage {
	
	By checkOutTable= By.xpath("//table[@class=\"cart-items\"]//tbody//tr");
	By totalPrice=  By.xpath("//div[@class=\"totals\"]//p");

	
	
	public CheckoutPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
	}
	
	public Map<String, Map<String,String>> getProductDetails()
	{
		try {
		Map<String, Map<String,String>> productDetails= new HashMap<>();
		WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		List<WebElement> tableRows= wt.until(ExpectedConditions.presenceOfAllElementsLocatedBy(checkOutTable));
		
		for(int i=1;i<=tableRows.size();i++)
		{
			String productDescriptionXpath= String.format("//table[@class=\"cart-items\"]//tbody//tr[@id='CartItem-%d']//td",i);
			List<WebElement> productRows= wt.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(productDescriptionXpath)));
			Map<String,String> productDescription = new HashMap<>();
			String productKey="";
			for(WebElement e: productRows)
			{
				String className= e.getAttribute("class");
				switch(className)
				{
				case "cart-item__details":
					productKey= e.findElement(By.tagName("a")).getText();
					productDescription.put("ProductName", productKey);
					productDescription.put("ProductPrice", e.findElement(By.tagName("div")).getText());
					productDescription.put("ProductCountry", e.findElement(By.tagName("dd")).getText());
					break;
				case "cart-item__quantity":
					productDescription.put("ProductQty", e.findElement(By.tagName("input")).getAttribute("value"));
					break;
				case "cart-item__totals right small-hide":
					productDescription.put("ProductTotal", e.findElement(By.tagName("span")).getText());
					break;
				}
			}
			
			if(productKey!=null && !productKey.isEmpty())
			{
				productDetails.put(productKey, productDescription);
			}
		}
		
		return productDetails;
		}
		catch(Exception e)
		{
			return HandleException.handleException("CaptureProductDetails", "CheckOutPage", e, true, null, test);
			
		}
		

	}
	
	public double calculatePriceforAddedItems(int qty, int price)
	{
		
		double total = price*qty;
		return total;
	}
	
	public String totalPriceBeforeServices()
	{
		return getText(totalPrice, "Total Price on CheckOutPage");
	}
	
	

}
