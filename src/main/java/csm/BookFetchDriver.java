package csm;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import csm.controllers.MainController;
import csm.utilities.DAO;


public class BookFetchDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainController.getInstance();
		
//		DAO db = DAO.getInstance();
//		try {
//      ResultSet student = db.selectAllFrom("person natural join student");
//      ResultSetMetaData metaData = student.getMetaData();
//      int i = metaData.getColumnCount();
//      
//      for(int x = 1 ; x <= i; x++){
//        System.out.println(metaData.getColumnName(x));
//      }
//      
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
		
	}

}
  