package csm.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;

import csm.model_views.CartBooksModelView;
import csm.models.StudentModel;

public class JTableFactory {
  public static JTable constructStudentJTable(ResultSet rs) throws SQLException{
//    Vector<String> columnNames = DAO.getInstance().getColumns(rs);
//    Vector<Vector<Object>> data = DAO.getInstance().getData(rs);
    JTable table = new JTable(new StudentModel());
    table.setFillsViewportHeight(true);
    table.setCellEditor(null);
    return table;
  }
  
  public static JTable constructCartJTable(CartBooksModelView model){
    JTable table = new JTable(model);
    table.setFillsViewportHeight(true);
    table.setCellEditor(null);
    return table;
  }
}
