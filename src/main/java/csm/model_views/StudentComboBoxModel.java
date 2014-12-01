package csm.model_views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import csm.classes.Student;
import csm.utilities.DAO;

public class StudentComboBoxModel implements ComboBoxModel<Student> {
  private Student selected;
  private ArrayList<Student> students;
  private final String[] sqlFields = {"id","first_name", "last_name"};
  public StudentComboBoxModel(){
    students = new ArrayList<Student>();
    try {
      ResultSet rs = DAO.getInstance().selectSomeFrom("person natural join student", sqlFields);
      while(rs.next()){
        students.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void addListDataListener(ListDataListener arg0) {
    // TODO Auto-generated method stub
    
  }

  public Student getElementAt(int arg0) {
    return students.get(arg0);
  }

  public int getSize() {
    // TODO Auto-generated method stub
    return students.size();
  }

  public void removeListDataListener(ListDataListener arg0) {
    // TODO Auto-generated method stub
  }

  public Object getSelectedItem() {
    return selected;
  }

  public void setSelectedItem(Object arg0) {
    selected = (Student) arg0;
 }

}