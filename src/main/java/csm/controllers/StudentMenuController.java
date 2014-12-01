package csm.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import csm.views.AbstractView;
import csm.views.StudentMenuView;

public class StudentMenuController extends AbstractController{
  private final StudentMenuView view = new StudentMenuView();

  public StudentMenuController() {
    addActionListeners();
  }

  private void addActionListeners() {
    view.getStudentsBtn().addActionListener(getStudentAction());
    view.getCartsBtn().addActionListener(cartBtnEvent());
  }

  public AbstractView getView() {
    return view;
  }
  
  private ActionListener cartBtnEvent(){
    return new ActionListener(){

      public void actionPerformed(ActionEvent e) {
        tearDownView();
        CartController controller = null;
          try {
            controller = new CartController();
          } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
        /**
         * Set close event to re-open main menu.
         */
        controller.setWindowCloseEvent(subWindowCloseEvent());
      }
      
    };
  }

  private ActionListener getStudentAction() {
    return new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        tearDownView();

        StudentController controller = null;
        try {
          controller = new StudentController();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        /**
         * Set close event to re-open main menu.
         */
        controller.setWindowCloseEvent(subWindowCloseEvent());

      }
    };
  }
  
  private WindowAdapter subWindowCloseEvent(){
    return new WindowAdapter() {
      public void windowClosing(WindowEvent event) {
        setUpView();
      }
    };
  }
}
