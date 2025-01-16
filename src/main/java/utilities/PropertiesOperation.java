package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import environment.EnvironmentSelection;

public class PropertiesOperation {
	
	static Properties prop=new Properties();

	public static Properties init_Property()
	{
		FileInputStream input = null;
		
		try {
			input = new FileInputStream(EnvironmentSelection.environmentSetting());
			prop.load(input);
			
		} catch (FileNotFoundException e) 
		{
			System.err.println("Properties Operation failed due to"+ e.getMessage());
			throw new RuntimeException("Failed to load properties file.", e);
		}
		catch(IOException e)
		{
			System.err.println("Properties Operation failed due to"+ e.getMessage());
			throw new RuntimeException("Failed to load properties file.", e);
		}
		finally
		{
			if(input!=null)
			{
				try {
					input.close();
				} catch (IOException e) {
					System.err.println("Unable to close the File loaded for Initializing Properies"+ e.getMessage());
				}
			}
		}
		return prop;
		
	}
	
	public static Properties getProperty()
	{
		return prop;
	}

}
