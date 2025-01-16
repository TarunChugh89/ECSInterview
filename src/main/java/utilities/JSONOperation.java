package utilities;

import java.io.File;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

public class JSONOperation {
	
	public JSONObject readJSONObject(String filePath)
	{
		try
		{
			JSONTokener jsontoken= new JSONTokener(new FileReader(filePath));
			JSONObject jsonobject= new JSONObject(jsontoken);
			return jsonobject;
		}
		catch(Exception e)
		{
			//return HandleException.handleException("JSON File Loading", filePath, e, true, null, test);
			throw new RuntimeException("Execution stopped due to an exception.", e);
		}
		
	}
	
	public JSONObject getCustomerDataFromJSON(String filePath, String key)
	{
		if(readJSONObject(filePath).has(key)) {
		return readJSONObject(filePath).getJSONObject(key);}
		else
		{
			//return HandleException.handleException("CustomerDataRead", filePath, new Exception("Key is not valid"),true,null,test);
			throw new RuntimeException("Execution stopped due to an exception.");
		}
	}
	
	public JSONObject getPriceDataFromJSON(String filePath, String Region)
	{
		if(readJSONObject(filePath).getJSONObject("price").has(Region)) {
			return readJSONObject(filePath).getJSONObject("price").getJSONObject(Region);}
		else
		{
			//return HandleException.handleException("CustomerDataRead", filePath, new Exception("Key is not valid"),true,null,test);
			throw new RuntimeException("Execution stopped due to an exception.");
		}
		
	}
	
	public JSONObject getZoneDataFromJSON(String filePath, String Region)
	{
		if(readJSONObject(filePath).getJSONObject("zoneCharges").has(Region)) {
			return readJSONObject(filePath).getJSONObject("zoneCharges").getJSONObject(Region);}
		else
		{
			//return HandleException.handleException("ZoneDataRead", filePath, new Exception("Key is not valid"),true,null,test);
			throw new RuntimeException("Execution stopped due to an exception");
		}
		
	}
	
	public JSONArray getProductFromJSON(String filePath, String key)
	{
		if(readJSONObject(filePath).has(key)) {
			return readJSONObject(filePath).getJSONArray(key);}
		else
		{
			//return HandleException.handleException("ProductInformationRead", filePath, new Exception("Key is not valid"),true,null,test);
			throw new RuntimeException("Execution stopped due to an exception.");
		}
		
	}
	
	
}
