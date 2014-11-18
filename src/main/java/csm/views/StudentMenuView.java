package csm.views;

import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StudentMenuView extends AbstractView {

	public static String title = "Student Menu";
	
	private JPanel container;
	
	private final JButton studentsBtn;
	
	public StudentMenuView() {
		super(title);
		// TODO Auto-generated constructor stub
		container = new JPanel();
		
		studentsBtn = new JButton("Add/Edit Student");
		
		addToPanel(container, studentsBtn);
		add(container);
		buildView();
	}

}
