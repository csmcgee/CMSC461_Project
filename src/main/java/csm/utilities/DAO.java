package csm.utilities;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import com.google.common.base.Joiner;

public class DAO {

  private static DAO instance;
  private Connection connection = null;

  private final String database = "project_db";
  private final String username = "root";
  private final String password = "passwd";
  private final String url = "jdbc:mysql://localhost/" + database + "?user="
      + username + "&password=" + password;

  public static DAO getInstance() {
    if (instance == null) {
      instance = new DAO();
      return instance;
    }
    return instance;
  }

  public DAO() {
    try {
      connection = DriverManager.getConnection(url, username, password);
      connection.setAutoCommit(false);
    } catch (SQLException e) {
      connection = null;
    }
  }
  
  public ResultSet selectSomeFrom(String table, String[] fields) throws SQLException{
    Statement stmt = null;
    stmt = connection.createStatement();
    Joiner join = Joiner.on(",");
    String sql = "SELECT " + join.join(fields) + " from " + table + ";";
    return stmt.executeQuery(sql);
  }
  
  public ResultSet selectAllFrom(String table) throws SQLException{
    Statement stmt = null;
    stmt = connection.createStatement();
    String sql = "SELECT * from " + table + ";";
    return stmt.executeQuery(sql);
  }
  
  public Vector<String> getColumns(ResultSet rs) throws SQLException{
    Vector<String> columns = new Vector<String>();
    ResultSetMetaData metaData = rs.getMetaData();
    int length = metaData.getColumnCount();
    
    for(int i = 1 ; i <= length; i++){
      columns.add(metaData.getColumnName(i));
    }
    return columns;
  }
  
  public Vector<Vector<Object>> getData(ResultSet rs) throws SQLException {
    Vector<Vector<Object>> table = new Vector<Vector<Object>>();
    int length = rs.getMetaData().getColumnCount();
    while(rs.next()){
      Vector<Object> row = new Vector<Object>();
      for(int i = 1; i <= length; i++){
        row.add(rs.getObject(i));
      } 
      table.add(row);
    }
    return table;
  }
  
  public ResultSet selectAllFromWhere(String table, String condition) throws SQLException{
    Statement stmt = null;
    stmt = connection.createStatement();
    String sql = "SELECT * FROM " + table + " WHERE " + condition + ";";
    return stmt.executeQuery(sql);
  }
  
  public void deleteFromWhere(String table, String condition) throws SQLException{
    Statement stmt = null;
    stmt = connection.createStatement();
    String sql = "DELETE FROM " + table + " WHERE " + condition + ";"; 
    stmt.executeUpdate(sql);
    connection.commit();
  }
  
  public static int getRowCount(ResultSet rs){
    int count = 0;
    try {
      while(rs.next()){
        count++;
      }
      rs.beforeFirst();
      return count;
    }catch(SQLException e){
      e.printStackTrace();
    }
    return count;
  }
  
  public static Object getValueAt(ResultSet rs, int row, int column){
    Object retVal = null;
    try {
      for(int i = 0; rs.next(); i++){
        if(i == row){
          retVal = rs.getObject(column+1);
          rs.beforeFirst();
          break;
        }
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return retVal;
  }
  
  public static ArrayList<String> getRow(ResultSet rs, int row){
    ArrayList<String> rowArray = new ArrayList<String>();
    try{
      int columns = rs.getMetaData().getColumnCount();
      for(int i = 0; rs.next(); i++){
        if(i == row){
          for(int j = 1; j <= columns; j++){
            rowArray.add(rs.getString(j));
          }
          rs.beforeFirst();
          break;
        }
      }
    } catch (SQLException e){
      
    }
    return rowArray;
  }
  
  public Connection getConnection(){
    return connection;
  }
  
}
