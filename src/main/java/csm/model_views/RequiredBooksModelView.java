package csm.model_views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import csm.classes.Book;
import csm.models.StudentCartModel;
import csm.utilities.DAO;

public class RequiredBooksModelView extends AbstractTableModel {
	int universityId, sectionId, semesterId, courseId, year;
	public String[] header = {"ISBN", "Title", "Type", "Status", "Format", "Price", "Quantity"};
	public RequiredBooksModelView(int universityId, int sectionId, int semesterId, int courseId, int year){
		this.universityId = universityId;
		this.sectionId = sectionId;
		this.courseId = courseId;
		this.semesterId = semesterId;
		this.year = year;
	}
	
	public RequiredBooksModelView() {
	}
	
	  public String getColumnName(int column){
		    return header[column];
		  }
		  
		  public int getColumnCount() {
		    // TODO Auto-generated method stub
		    return header.length;
		  }

		  public int getRowCount() {		  
		    return DAO.getRowCount(constructResultSet());
		  }

		  public Object getValueAt(int arg0, int arg1) {
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

	protected ResultSet constructResultSet(){
		    return StudentCartModel.getRequiredBooks(universityId, courseId, semesterId, sectionId, year);
		  }
	  
}
