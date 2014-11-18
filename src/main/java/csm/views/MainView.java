package csm.views;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Main view which gives the user the option to select
 * a given module.
 * @author crow
 *
 */
public class MainView extends AbstractView{

	private static String title = "Main Menu";
	
	/**
	 * UID
	 */
	private static final long serialVersionUID = 2239092641567800316L;
	
	private JPanel container;

	private final JButton studentBtn;
	private final JButton customerServiceBtn;
	private final JButton adminBtn;
	private final JButton superAdminBtn;
	private final JButton reportsBtn;
	
	public MainView() {
		super(title);
		
		container = new JPanel();
		
		studentBtn = new JButton("Student");
		customerServiceBtn = new JButton("Customer Service");
		adminBtn = new JButton("Administrator");
		superAdminBtn = new JButton("Super Admin.");
		reportsBtn = new JButton("Reports");
		
		addToPanel(container, studentBtn, customerServiceBtn,
				adminBtn, superAdminBtn, reportsBtn);
		add(container);
		
		buildView();
	}
	
	public JButton getStudentBtn() {
		return studentBtn;
	}

	public JButton getCustomerServiceBtn() {
		return customerServiceBtn;
	}

	public JButton getAdminBtn() {
		return adminBtn;
	}

	public JButton getSuperAdminBtn() {
		return superAdminBtn;
	}

	public JButton getReportsBtn() {
		return reportsBtn;
	}

}
