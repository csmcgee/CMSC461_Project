package csm.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import csm.views.MainView;

public class MainController {
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
	
	private void setUp(){
		mainView.setVisible(true);
	}
	
	private void tearDown(){
		mainView.setVisible(false);
	}
	private ActionListener getStudentAction(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				tearDown();
				
				new StudentMenuController().setWindowCloseEvent(new WindowAdapter(){
					public void windowClosing(WindowEvent event){
						System.out.println("Closing");
						setUp();
					}
				});
			}		
		};
	}
	
}
