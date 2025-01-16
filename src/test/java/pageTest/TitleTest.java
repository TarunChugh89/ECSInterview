package pageTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import base.TestBase;
import constant.ConstantValue;
import page.HomePage;
import page.ProductDescription;
import utilities.PropertiesOperation;

public class TitleTest extends TestBase {
	
	@DataProvider(name="firstHomePageOptionsTest")
	public Object[][] getDataForFirstHomePageTitleOptionsTest()
	{
		Object[][] data= new Object[][]{
			{"Home","Catalog","Contact","Terms Of Service","English","USA"},
			//{"Maison","Catalogue","Contact","Conditions d'utilisation","Français","Canada"},
			//{"Hogar","Catalogar","Contacto","Condiciones de servicio","Español","Canada"}
			
		};
		return data;
		
	}
	
	@Test(dataProvider = "firstHomePageOptionsTest")
	public void firstHomePageTitleOptionsTest(String[] titleOptions)
	{
	
		setTestStep("Open Home Page");
		DriverContext().get(PropertiesOperation.init_Property().getProperty("url"));
		DriverContext().manage().window().maximize();
		testStep().info("Home Page is displayed");
		setTestStep("Validate the Home Page Title Options");
		HomePage homePage= new HomePage(DriverContext(),testStep(),titleOptions[titleOptions.length-2]);
		testStep().info("Select the Country");
		homePage.selectCountry(titleOptions[titleOptions.length-1]);
		testStep().info("Select the Language");
		homePage.selectLanguage(titleOptions[titleOptions.length-2]);
		List<String> testOptions= new ArrayList<>(Arrays.asList(titleOptions).subList(0, titleOptions.length-2));
		List<String> pageOptions= homePage.getHomePageTitleOptions();
		testStep().info("Validating the Home Page Title options");
		assertEquals(testOptions, pageOptions);
		testStep().info("Validating the Home Page Main Title");
		assertEquals(homePage.getHomePageMainTitle(), ConstantValue.homePageTitle);
		testStep().info("Click on Search Button and Select the Product");
		homePage.selectProduct("Remote Installation Service + Dell OptiPlex Micro 7020 Bundle");
		setTestStep("Add the Product to Cart");
		ProductDescription productDesc= new ProductDescription(DriverContext(),testStep(),titleOptions[titleOptions.length-2]);
		productDesc.addToCart(5, true);
		getAssert().assertAll();
		
	}

}
