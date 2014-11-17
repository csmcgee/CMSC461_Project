package csm.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import csm.views.LoginView;

public class LoginController {
	private final LoginView loginView = new LoginView();
	
	public LoginController(){
		addActionListeners();
	}	
	
	private void addActionListeners(){
		loginView.getLoginBtn()
			.addActionListener(loginAction());
		
	}
	
	private ActionListener loginAction(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String username = loginView.getUsernameField().getText();
				String password = loginView.getPasswordField().getText();
				login(username, password);
				System.exit(1);
			}		
		};
	}
	
	private boolean login(String username, String password){
		System.out.println("Logged in, closing now");
		return true;
	}
}
