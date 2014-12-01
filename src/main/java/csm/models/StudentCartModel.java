package csm.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.joda.time.DateTime;

import csm.classes.Book;
import csm.classes.CreditCard;
import csm.utilities.DAO;

public class StudentCartModel {
  public static ResultSet getStudentCart(int studentId) throws SQLException{
    Connection con = DAO.getInstance().getConnection();
    Statement stmt = null;
    stmt = con.createStatement();
    String sql = "select cart_items.ISBN, book.title, cart_items.type,"
        + " cart_items.status, cart_items.format, inventory.price,"
        + " cart_items.quantity from cart_items join inventory"
        + " on cart_items.ISBN = inventory.ISBN AND cart_items.type = inventory.type"
        + " AND cart_items.status = inventory.status AND cart_items.format = "
        + "inventory.format join book on cart_items.ISBN = book.ISBN "
        + "where cart_items.id = " + studentId + ";";
    return stmt.executeQuery(sql);
  }
  
  public static void removeBookFromCart(int studentId, String ISBN,
    String type, String status, String format) throws SQLException{
    Connection con = DAO.getInstance().getConnection();
    _removeBookFromCart(con, studentId, ISBN, type, status, format);
    con.commit();
  }
  
  private static void _removeBookFromCart(Connection con, int studentId, String ISBN,
    String type, String status, String format) throws SQLException{
    Statement stmt = null;
    stmt = con.createStatement();
    
    String sql = "DELETE FROM cart_items WHERE ISBN = \"" + ISBN + "\" AND "
        + "type = \"" + type + "\" AND status = \"" + status + "\" AND "
            + "format = \"" + format + "\" AND id = " + studentId + ";";
    stmt.executeUpdate(sql);
  }
  
  public static void addToCart(int studentId, 
      String ISBN, String type, String status, String format, int quantity) throws SQLException{
    Connection con = DAO.getInstance().getConnection();
    _addToCart(con, studentId, ISBN, type, status, format, quantity);
    con.commit();
  }
  
  private static void _addToCart(Connection con, int studentId, 
      String ISBN, String type, String status, String format, int quantity) throws SQLException{
    Statement stmt = null;
    stmt = con.createStatement();
    
    String sql = "INSERT INTO "
        + "cart_items (id, ISBN, type, status, format, quantity) "
        + "VALUES "
        + "("+studentId+",\""+ISBN+"\",\""+type+"\","
        + "\""+status+"\",\""+format+"\","+quantity+")";
    stmt.executeUpdate(sql);
  }
  
  private static void _clearCart(Connection con, int studentId) throws SQLException{
    Statement stmt;
    stmt = con.createStatement();
    
    String sql = "DELETE FROM cart_items WHERE id = " + studentId + ";";
    stmt.executeUpdate(sql);
  }
  
  //@TODO CHECK QUANTITY LEFT IN INVENTORY
  public static void createOrderFromBooks(int studentId, ArrayList<Book> books,
      CreditCard cc, String orderType, double total) throws Exception{
    Connection con = DAO.getInstance().getConnection();
    Statement stmt = null;
    stmt = con.createStatement();
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    String orderDate = dateFormat.format(date);
    
    String sql = "INSERT INTO book_order(date_created, shipping_type, cc_num, cc_name,"
        + "cc_exp, cc_type, status, total) VALUES(\""+orderDate+"\","
        + "\""+orderType+"\",\""+cc.number+"\",\""+cc.name+"\","
        + "\""+cc.expiration+"\",\""+cc.type+"\",1,\""+total+"\");";
    
    stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
    ResultSet rs = stmt.getGeneratedKeys();
    int orderId = 0;
    if(rs.next()){
      orderId = rs.getInt(1);
    }
    
    
    
//    // Check books
//    if(true == false){
//      throw new Exception("One of the books are unavailable. Pick one that is currently in inventory.");
//    }
//    
   for(Book book: books){
     stmt = con.createStatement();
     sql = "INSERT INTO transaction(cart_id, order_id, ISBN, "
         + "status, format, type, quantity) VALUES ("+studentId+","
         + orderId + ",\""+book.ISBN+"\",\""+book.status+"\",\""+book.format+"\""
         + ",\""+book.type+"\",\""+book.quantity+"\");";
     stmt.executeUpdate(sql);
   }
   
   _clearCart(con, studentId);
   
   con.commit();
  }
  
  /**
   * Returns true when avaialble.
   */
  private static boolean checkBookAvailability(String ISBN, 
      String type, String status, String format){
    return true;
  }
}