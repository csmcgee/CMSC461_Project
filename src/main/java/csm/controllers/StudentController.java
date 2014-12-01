package csm.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;

import csm.models.StudentModel;
import csm.models.UniversityModel;
import csm.utilities.DAO;
import csm.views.AbstractView;
import csm.views.StudentView;

public class StudentController extends AbstractController {

  private final StudentView view = new StudentView();
  private static int selected_student_id;
  
  public StudentController() throws SQLException{
    addEventListeners();
  }
  
  public void addEventListeners(){
    view.getDeleteBtn().addActionListener(deleteEvent());
    view.getAddNewBtn().addActionListener(addStudentEvent());
    view.getTable().addMouseListener(tableDblClickEvent());
    view.getSaveBtn().addActionListener(editStudentEvent());
  }
  
  @Override
  public AbstractView getView() {
    return view;
  }
  
  private ActionListener deleteEvent(){
    return new ActionListener(){

      public void actionPerformed(ActionEvent arg0) {
        JTable table = view.getTable();
        int id =  Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), StudentModel.ID).toString());
        StudentModel.deleteStudent(id);
        view.repaint();
      }
      
    };
  }
  
  private ActionListener editStudentEvent(){
    return new ActionListener(){
      public void actionPerformed(ActionEvent arg0){
        String[] values = {
            view.getFirstNameField().getText(),
            view.getLastNameField().getText(),
            view.getEmailField().getText(),
            view.getStreetNumField().getText(),
            view.getStreetNameField().getText(),
            view.getCityField().getText(),
            view.getStateField().getText(),
            view.getZipField().getText(),
            view.getTelephoneField().getText()
        };
        
        String status = (view.getUnderGradRadio().isSelected()) ? "Undergraduate" : "Graduate";
        
        String [] universityValues = {
            Integer.toString(UniversityModel.getUniversityIDByName(view.getUniversityBox().getSelectedItem().toString())),
            Integer.toString(selected_student_id),
            status,
            view.getYearField().getText()
        };
        try {
          
          StudentModel.updateStudent(selected_student_id, 
              values, view.getBirthDateField().getText(), 
              universityValues, getSelectedMajorIds());
          view.clearFields().repaint();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    };
  }
  
  private ActionListener addStudentEvent(){
    return new ActionListener(){

      public void actionPerformed(ActionEvent arg0) {
        String[] values = {
            view.getFirstNameField().getText(),
            view.getLastNameField().getText(),
            view.getEmailField().getText(),
            view.getStreetNumField().getText(),
            view.getStreetNameField().getText(),
            view.getCityField().getText(),
            view.getStateField().getText(),
            view.getZipField().getText(),
            view.getTelephoneField().getText()
        };
        
        String status = (view.getUnderGradRadio().isSelected()) ? "Undergraduate" : "Graduate";
        
        String [] universityValues = {
            Integer.toString(UniversityModel.getUniversityIDByName(view.getUniversityBox().getSelectedItem().toString())),
            "LAST_INSERT_ID()",
            status,
            view.getYearField().getText()
        };
        try {
          StudentModel.insertStudent(values,
              view.getBirthDateField().getText(), universityValues,
              getSelectedMajorIds());
          view.clearFields().repaint();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      
    };
  }
  
  private ArrayList<Integer> getSelectedMajorIds(){
    int[] indices = view.getMajorField().getSelectedIndices();
    ArrayList<String> majorList = StudentModel.getAllMajors();
    ArrayList<Integer> majors = new ArrayList<Integer>();
    String name;
    for(int x: indices){
      name = majorList.get(x);
      majors.add(StudentModel.getMajorIdByName(name));
      
    }
    return majors;
  }
  
  private MouseListener tableDblClickEvent(){
    return new MouseListener(){

      public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){
          JTable table = view.getTable();
          int id =  Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), StudentModel.ID).toString());
          ResultSet rs = null;
          try {
            selected_student_id = id;
            rs = StudentModel.getStudent(id);
            
          } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
          
          
          view.setFields(rs,
              StudentModel.getStudentMajorIndices(selected_student_id));
          
        }
      }

      public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
      }

      public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
      }

      public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
      }

      public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
      }
      
    };
  }

}
