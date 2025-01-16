package utilities;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;


import com.aventstack.extentreports.ExtentTest;
import com.google.gson.Gson;


public class LocalizationUtil {
	
	public static ThreadLocal<Map<String,Map<String,String>>> threadlocatorData= new ThreadLocal<Map<String,Map<String,String>>>();
	
	@SuppressWarnings("unchecked")
	public static void loadLocators(String filePath,ExtentTest test)
	{
		try
		{	
			Map<String,Map<String,String>> filDataLocator= new HashMap<>();
			filDataLocator=new Gson().fromJson(new FileReader(filePath), Map.class);
			threadlocatorData.set(filDataLocator);
		}
		catch(Exception e)
		{
			HandleException.handleException("JSON Language Specific Initialization", filePath, e, true, null, test);
		}
	}
	
	  public static Map<String,String> getLocators(String language,ExtentTest test) {
	        Map<String,String> languageLocator= new HashMap<String,String>();
	        for(Map.Entry<String,Map<String,String>> temp: threadlocatorData.get().entrySet())
	        {
	        	String locator= temp.getValue().get(language);
	        	languageLocator.put(temp.getKey(), locator);
	        }
	        
	        return languageLocator;
	    }
	
	
	

}
