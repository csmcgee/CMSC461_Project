package csm.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import csm.views.AbstractView;
import csm.views.MainView;

public class MainController extends AbstractController {
	private final MainView mainView = new MainView();
	
	private static MainController instance = null;
	
	public static MainController getInstance(){
		if(instance == null){
			instance = new MainController();
		}
		return instance;
	}
	
	public MainController(){
		addActionListeners();
	}
	
	private void addActionListeners(){
		mainView.getStudentBtn()
			.addActionListener(getStudentAction());
		
	}
	  
	private ActionListener getStudentAction(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				tearDownView();
				
				StudentMenuController sController = new StudentMenuController();
				
				/**
				 * Set close event to re-open main menu.
				 */
				sController.setWindowCloseEvent(new WindowAdapter(){
          public void windowClosing(WindowEvent event){
            setUpView();
          }
        });
				
			}		
		};
	}

  @Override
  public AbstractView getView() {
    return mainView;
  }
	
}
