package base;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class TestBase extends BaseTest {
	
	public void assertEquals(Object expected, Object actual)
	{
		if(expected.equals(actual))
		{
			testStep().pass(String.format("Object value Match Passed.Expected: %s, Actual: %s",expected,actual));
		}
		else
		{
			String errorMessage=String.format("Object value Match Failed.Expected: %s, Actual: %s",expected,actual);
			testStep().fail(errorMessage);
			getAssert().fail(errorMessage);
		}
	}
	
	public void assertEquals(Object[] expected, Object[] actual)
	{
		if(Arrays.equals(expected,actual))
		{
			testStep().pass(String.format("Array Value Match Passed.Expected: %s, Actual: %s",Arrays.toString(expected),Arrays.toString(actual)));
		}
		else
		{
			String errorMessage=String.format("Array Value Match Failed.Expected: %s, Actual: %s",Arrays.toString(expected),Arrays.toString(actual));
			testStep().fail(errorMessage);
			getAssert().fail(errorMessage);
		}
	}
	
	public <T> void assertEquals(List<T> expected, List<T> actual)
	{
		if(expected.equals(actual))
		{
			testStep().pass(String.format("List Values Match.Expected: %s, Actual: %s",expected,actual));
		}
		else
		{
			String errorMessage=String.format("List Values Match Failed.Expected: %s, Actual: %s",expected,actual);
			testStep().fail(errorMessage);
			getAssert().fail(errorMessage);
		}
	}
	
	public void assertTrue(boolean condition,String locatorvalue, String message)
	{
		if(condition)
		{
			testStep().pass(String.format("Assertion PASS: [%s] is [%s]",locatorvalue,message));
		}
		else
		{
			testStep().fail(String.format("Assertion FAIL: [%s] is [%s]",locatorvalue,message));
			getAssert().fail(String.format("Assertion FAIL: [%s] is [%s]",locatorvalue,message));
		}
	}
	public String fetchRetailerAddressData(String retailerId)
	 {	 
		 String query=String.format("SELECT * FROM customer.retailerdata where RETAILERCODE='%s'", retailerId);
		 System.out.println(query);
		 return query;
	 }

	public String currencyConverter(double price,String country)
	{
		NumberFormat currencyFormat=null;
		if(price<0)
		{
			throw new IllegalArgumentException("Price cannot be negative");
		}
		switch(country.toUpperCase())
		{
		case "USA":
			 currencyFormat= NumberFormat.getCurrencyInstance(Locale.US);
			break;
		case "CANADA":
			 currencyFormat= NumberFormat.getCurrencyInstance(Locale.CANADA);
			break;
		default:
			throw new IllegalArgumentException("Unsupported Country"+ country);
		}
				
		return currencyFormat.format(price);
	}
	
}
