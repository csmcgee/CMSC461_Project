package csm.controllers;

import java.awt.event.WindowAdapter;

import csm.views.StudentMenuView;

public class StudentMenuController {
	private final StudentMenuView view = new StudentMenuView();
	
	public StudentMenuController(){
		
	}
	
	public StudentMenuView getView() {
		return view;
	}
	
	/**
	 * @todo make an abstract method
	 */
	public void setWindowCloseEvent(WindowAdapter event){
		view.addWindowListener(event);
	}
}
