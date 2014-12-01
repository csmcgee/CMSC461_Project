package csm.tests;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import java.sql.Connection;

import junit.framework.TestCase;


public class JDBCTestSuite extends TestCase{
	private Connection connection = null;
	private String database = "TEST";
	private String username = "root";
	private String password = "passwd";
	
	public void setUp(){
 		try 
 		{
 		  String dburl="jdbc:mysql://localhost/"+database+"?user="+username+"&password="+password;
 			connection = DriverManager.getConnection( dburl, username,password); 
 		} 
	    catch (SQLException e) 
	    {
	    	connection = null;
 		}
	}
	
	@Test
	public void testConnection(){
		assertNotNull("Connection to the database failed.", connection);
	}
	
}
