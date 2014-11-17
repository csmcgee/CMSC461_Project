package csm.views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import csm.utilities.SpringUtilities;

public class LoginView extends AbstractView {

	/**
	 * Generated Serial UID
	 */
	private static final long serialVersionUID = -2115439997133664329L;

	private static String title = "Login";
	
	private JPanel container;
	private JPanel form;

	private final JTextField usernameField;
	private final JTextField passwordField;
	private final JButton loginBtn;
	
	public LoginView(){
		super(title);
		
		// Instantiate components
		container = new JPanel();
		form = new JPanel(new SpringLayout());
		usernameField = new JTextField(15);
		passwordField = new JTextField(15);
		loginBtn = new JButton("Login");
		
		// Add to panel
		form.add(new JLabel("Email:"));
		form.add(usernameField);
		form.add(new JLabel("Password:"));
		form.add(passwordField);
		SpringUtilities.makeCompactGrid(form, 2, 2, 5, 5, 5, 5);
		container.add(form);
		container.add(new JPanel().add(loginBtn));
		add(container);
		
		// Construct View
		buildView();
	}
	
	/**
	 * Getters
	 */
	
	public JTextField getUsernameField() {
		return usernameField;
	}

	public JTextField getPasswordField() {
		return passwordField;
	}

	public JButton getLoginBtn() {
		return loginBtn;
	}
}
