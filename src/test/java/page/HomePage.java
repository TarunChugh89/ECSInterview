package page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import base.BasePage;
import utilities.LocalizationUtil;

public class HomePage extends BasePage {
	
	By homePageTitleOptions= By.xpath("//a[contains(@id,\"HeaderMenu\")]/span");
	By homePageMainTitle= By.xpath("//h2/strong");
	By languageButton= By.xpath("//button[@aria-describedby=\"HeaderLanguageLabel\"]");
	By languageOptions = By.xpath("//ul[@id=\"HeaderLanguageList\"]/li/a/span");
	By countryButton= By.xpath("//button[@aria-describedby=\"HeaderCountryLabel\"]");
	By countryOptions= By.xpath("//div[@id=\"HeaderCountry-country-results\"]//a//span[@class=\"localization-form__currency motion-reduce\"]/parent::a");
	By searchInputText= By.xpath("//input[@id=\"Search-In-Modal\"]");
	By searchProductOptions= By.xpath("//div[@id=\"product-grid\"]//li//div[@class=\"card__content\"]//a");
	String filePath= "./src/test/resources/LocatorData/homePage.json";
	Map<String,String> locatorData= new HashMap<>();
	
	
	
	public HomePage(WebDriver driver, ExtentTest test, String language)
	{
		super(driver,test);
		LocalizationUtil.loadLocators(filePath, test);
		this.locatorData=LocalizationUtil.getLocators(language,test);
		
	}
	
	public By getSearchButtonXpath()
	{
		return By.xpath(locatorData.get("searchBtn"));
	}
	
	public List<String> getHomePageTitleOptions()
	{
		return getTextOptions(homePageTitleOptions, "homePageTitle"); 
	}
	
	public String getHomePageMainTitle()
	{
		return getText(homePageMainTitle, "HomePageMainTitle");
	}
	
	
	public void selectLanguage(String language)
	{
		click(languageButton, "Select Language Button");
		selectFromListByCollection("text", languageOptions, language, "language Options");
	}
	
	public void selectCountry(String country)
	{
		String shortCountry="";
		if(country.equalsIgnoreCase("USA"))
		{
			shortCountry="US";
		}
		else if(country.equalsIgnoreCase("CANADA"))
		{
			shortCountry="CA";
		}
		else {
			String errorMessage= String.format("Illegal country [%s] is being selected", country);
			test.fail(errorMessage);
			throw new IllegalArgumentException(errorMessage);
			
		}
		click(countryButton, "Select Country Button");
		selectFromListByCollection("value", countryOptions, shortCountry, "data-value");
		
	}
	
	public void clickSearchButton()
	{
		click(getSearchButtonXpath(), "SearchButton");
	}
	
	public void searchProduct(String productName)
	{
		clickSearchButton();
		inputText(searchInputText, productName, "Search Item");
		inputKeys(searchInputText, Keys.ENTER, " Search Item");
	}
	
	public void selectProduct(String productName)
	{
		searchProduct(productName);
		selectFromListByCollection("text", searchProductOptions, productName, "ProductList");
		
	}
	

}
