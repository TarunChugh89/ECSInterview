package pageTest;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import page.CheckoutPage;
import page.HomePage;
import page.ProductDescription;
import utilities.DatabaseOperation;
import utilities.JSONOperation;
import utilities.PropertiesOperation;

public class ProductTest extends TestBase {
	
	@DataProvider(name="TestDataSetup")
		public Object[][] fetchDataforTest()
		
		{
			
			JSONOperation jsop= new JSONOperation();
			DatabaseOperation dop= new DatabaseOperation();
			
			String[] testCaseFile= {
					"C:\\Eclipse\\Workspace\\ECSInterview\\src\\test\\resources\\TestData\\Test\\TestCase_017.json"
					};
			String pricePath="C:\\Eclipse\\Workspace\\ECSInterview\\src\\test\\resources\\TestData\\Product_Price.json";
			String zonePath="C:\\Eclipse\\Workspace\\ECSInterview\\src\\test\\resources\\TestData\\Zone_Charge.json";
			
			Object[][] data= new Object[testCaseFile.length][];
			
			for(int i=0;i<data.length;i++)
			{
			JSONObject customerData= jsop.getCustomerDataFromJSON(testCaseFile[i], "customer");
			HashMap<String,String> addressData=dop.fetchDBResults(fetchRetailerAddressData(customerData.getString("retailerID")), "jdbc:mysql://localhost:3306/customer", "root", "Swift@91!");
			JSONObject priceData= jsop.getPriceDataFromJSON(pricePath, addressData.get("COUNTRY DESC"));
			JSONObject zoneData= jsop.getZoneDataFromJSON(zonePath, addressData.get("COUNTRY DESC"));
			JSONArray productDataList= jsop.getProductFromJSON(testCaseFile[i], "products");
			data[i]= new Object[] {productDataList, customerData, priceData, zoneData, addressData};
			}
			return data;
		}
		
	@Test(dataProvider = "TestDataSetup")
	public void firstTest(JSONArray productList, JSONObject customerData,JSONObject priceData, JSONObject zoneData, Map<String,String> addressData)
	{
		setTestStep("1. Navigate to Home Page and Maximize window");
		DriverContext().get(PropertiesOperation.init_Property().getProperty("url"));
		DriverContext().manage().window().maximize();
		setTestStep("2. Select the Language and Country");
		HomePage homePage= setupHomePage(addressData, customerData);
		setTestStep("3. Search the Product and Add to Cart");
		ProductDescription productDescription= new ProductDescription(DriverContext(),testStep(),customerData.getString("language"));
		addToCart(productList, homePage, productDescription);
		CheckoutPage checkoutPage= new CheckoutPage(DriverContext(),testStep());
		validateCartDetails(productList, priceData,addressData, checkoutPage, customerData);
		
		
		
	}
	
	public HomePage setupHomePage(Map<String,String> addressData,JSONObject customerData)
	{
		HomePage homePage= new HomePage(DriverContext(),testStep(),customerData.getString("language"));
		homePage.selectCountry(addressData.get("COUNTRY DESC"));
		homePage.selectLanguage(customerData.getString("language"));
		return homePage;
	}

	
	public void addToCart(JSONArray productList,HomePage homePage, ProductDescription productDescription)
	{
		for(int i=0; i< productList.length();i++)
		{
			JSONObject productData= productList.getJSONObject(i);
			homePage.selectProduct(productData.getString("name"));
			productDescription.addToCart(productData.getInt("quantity"), productData.getBoolean("appointment"));
		}
	}
	
	public void validateCartDetails(JSONArray productList,JSONObject price, Map<String, String> addressData, CheckoutPage cop,JSONObject customerData)
	{
		Map<String, Map<String, String>> cartProductList =cop.getProductDetails();
		for(int i=0;i<productList.length();i++)
		{
			JSONObject productData= productList.getJSONObject(i);
			Map<String,String> productInformation= cartProductList.get(productData.getString("name"));
			assertProductDetails(productData,productInformation, price, addressData, cop,customerData);
		}
		
	}
	
	public void assertProductDetails(JSONObject productData,Map<String,String> productInformation, JSONObject price,Map<String, String> addressData,CheckoutPage cop,JSONObject customerData)
	{
		assertEquals(productData.getString("name"), productInformation.get("ProductName"));
		assertEquals(currencyConverter(price.getDouble(productData.getString("name")), addressData.get("COUNTRY DESC")), productInformation.get("ProductPrice"));
		assertEquals(String.valueOf(productData.getInt("quantity")), productInformation.get("ProductQty"));
		assertEquals(currencyConverter(cop.calculatePriceforAddedItems(productData.getInt("quantity"), price.getInt(productData.getString("name"))),addressData.get("COUNTRY DESC")), productInformation.get("ProductTotal"));
	}
	
	public Map<String,Object> fetchTotalPriceAndQty(JSONArray productList,JSONObject price,CheckoutPage cop)
	{
		 double totalPrice = 0;
		 int qty = 0;
		 Map<String,Object> CheckoutData= new HashMap<>();
		 for(int i=0;i<productList.length();i++)
		 {
			 JSONObject product = productList.getJSONObject(i);
			 totalPrice += cop.calculatePriceforAddedItems(product.getInt("quantity"), price.getInt(product.getString("name")));
		     qty += product.getInt("quantity");
		 }
		 
		 CheckoutData.put("TotalPrice", totalPrice);
		 CheckoutData.put("TotalQty", qty);
		 
		 return CheckoutData;
	}

}
