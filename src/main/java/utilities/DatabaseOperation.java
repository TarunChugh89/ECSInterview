package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

public class DatabaseOperation {
	
	// we always try to return single value post try, catch,finally
	//if you are returnin from try, make sure catch is throwing exception so that it is stopping there, if not catch should also return
	// finally will always execute before the return of try,catch- but use single return or both.
	
	
	public HashMap<String,String> fetchDBResults(String query,String connection,String username, String password)
	{
		Connection con=null;
		Statement smt=null;
		ResultSet rst= null;
		HashMap<String,String> data= new HashMap<String,String>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(connection,username,password);
			smt=con.createStatement();
			rst=smt.executeQuery(query);
			ResultSetMetaData md= rst.getMetaData();
			if(rst.next())
			{
				for(int i=1;i<=md.getColumnCount();i++)
				{
					data.put(md.getColumnName(i).toString(), rst.getString(i)!=null?rst.getString(i):"");
				}
			}
			
		} catch (Exception e) {
		        //HandleException.handleException("SQLRead", query, e, true, null, test);
				System.err.println(e.getMessage());
				throw new RuntimeException("Execution stopped due to an exception.", e); 
		}
		finally
		{
			try
			{
				if(rst!=null)
					rst.close();
				if(con!=null)
				con.close();
				if(smt!=null)
				smt.close();
			}
			catch(Exception e)
			{
				String closeError = "Unable to close the connection due to the following reason: " + e.getMessage();
				System.err.println(closeError);
	            //test.warning(closeError);
			}
			
		}
		
		return data;
		
	}

}
