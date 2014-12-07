package csm.model_views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import csm.classes.Book;
import csm.models.StudentCartModel;
import csm.utilities.DAO;

public class CartBooksModelView extends AbstractTableModel{

  public String[] header = {"ISBN", "Title", "Type", "Status", "Format", "Price", "Quantity"};
  private int studentId = -1;
  
  public CartBooksModelView(){
    super();
  }
  
  public String getColumnName(int column){
    return header[column];
  }
  
  public int getColumnCount() {
    // TODO Auto-generated method stub
    return header.length;
  }

  public int getRowCount() {
    if(studentId == -1){
      return 0;
    }
    
    return DAO.getRowCount(constructResultSet());
  }

  public Object getValueAt(int arg0, int arg1) {
    if(studentId == -1){
      return 0;
    }
    return DAO.getValueAt(constructResultSet(), arg0, arg1);
  }
  
  public Book getRow(int row){
    ArrayList<String> rowArray = DAO.getRow(constructResultSet(), row);
    int quantity = Integer.parseInt(rowArray.get(6));
    String ISBN = rowArray.get(0);
    String type = rowArray.get(2);
    String status = rowArray.get(3);
    String format = rowArray.get(4);
    double price = Double.parseDouble(rowArray.get(5));
    return new Book(quantity, ISBN, type, status, format, price);
  }
  
  public void setStudentId(int id){
    studentId = id;
  }
  
  protected ResultSet constructResultSet(){
    try {
      return StudentCartModel.getStudentCart(studentId);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
  
  public ArrayList<Book> getCartBooks(){
    ArrayList<Book> books = new ArrayList<Book>();
    int rows = getRowCount();
    for(int i = 0; i < rows; i++){
      books.add(getRow(i));
    }
    return books;
  }
  
  public double calculateTotal(ArrayList<Book> books){
    double total = 0.0;
    for(Book book: books){
      total += book.price;
    }
    return total;
  }
}
