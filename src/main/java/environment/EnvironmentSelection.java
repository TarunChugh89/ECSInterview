package environment;

public class EnvironmentSelection {
	
	public static String env="SIT";
	public static String envPath="";
	
	public static String environmentSetting()
	{
		switch(env.toLowerCase())
		{
		case "sit":
			envPath=System.getProperty("user.dir")+"/src/test/resources/config/"+env+".properties";
			break;
		case "preprod":
			envPath=System.getProperty("user.dir")+"/src/test/resources/config/"+env+".properties";
			break;
		default:
			System.err.println("Invalid environment: " + env + ". Failed to load property file under the config section.");
			throw new IllegalArgumentException("Invalid environment: " + env);
			
		}
		return envPath;
	}
	
	

}
