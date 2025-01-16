package pageTest;

import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;import org.bouncycastle.asn1.ocsp.ResponseData;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class APITest extends TestBase {
	

	@Test
	public void firstAPIReadTest()
	{
		Response res= given()
					  .when()
					  	.get("https://reqres.in/api/users/2")
					  	;
		
		System.out.println("Response Body"+ res.getBody().asString());
		Headers headers=res.getHeaders();
		
		for(Header value: headers)
		{
			System.out.println("Header Name is"+ value.getName()+" and Value is"+ value.getValue());
		}
		
		Map<String,String> cookieData= res.getCookies();
		for(Map.Entry<String, String> temp : cookieData.entrySet())
		{
			System.out.println("Cookie Name is "+ temp.getKey()+"and Cookie value is"+ temp.getValue());
		}
		
		System.out.println("Status code is"+ res.getStatusCode());
		
		JSONObject jsonresponse= new JSONObject(res.asString());
		
		System.out.println("FirstNameis:"+jsonresponse.getJSONObject("data").getString("first_name"));	
		System.out.println("Support URL is:"+jsonresponse.getJSONObject("support").getString("url"));
	}
	

	@Test
	public void secondAPIWriteTest()
	{
		
			String filepath="C:\\Eclipse\\Workspace\\ECSInterview\\src\\test\\resources\\TestData\\Post.json";
			JSONObject jsonData=null;
			try {
				jsonData = new JSONObject(new JSONTokener(new FileInputStream(filepath)));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsonData.put("email", "eve.holt@reqres.in");
			
			Response res= given()
						.header("Content-Type", "application/json")
						.body(jsonData.toString())
						.when()
						.post("https://reqres.in/api/register");
						
			
			JSONObject resposedata= new JSONObject(res.asString());
			System.out.println(res.getBody().asString());
			System.out.println(resposedata.get("id"));
	
		
	}
}
