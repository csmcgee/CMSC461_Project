package csm.views;

import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import csm.utilities.SpringUtilities;

public class StudentMenuView extends AbstractView {
  
  private static final long serialVersionUID = -7726518221814372083L;

  public static String title = "Student Menu";
	
	private JPanel container;
	
	private final JButton studentsBtn;
  private final JButton cartsBtn;
	private final JButton ordersBtn;
	private final JButton reviewsBtn;
	private final JButton ticketBtn;
	
	public StudentMenuView() {
		super(title);
		container = new JPanel(new SpringLayout());
		
		studentsBtn = new JButton("Add/Edit Student");
		cartsBtn = new JButton("Manage Carts");
		ordersBtn = new JButton("Create/Edit Orders");
		reviewsBtn = new JButton("Reviews");
		ticketBtn = new JButton("Trouble Ticket");
		
		
		addToPanel(container, studentsBtn, cartsBtn, ordersBtn,
				reviewsBtn, ticketBtn);
		add(container);
    SpringUtilities.makeGrid(container, 5, 1, 15, 5, 5, 10);
		buildView();
	}
	
	 public JButton getStudentsBtn() {
	    return studentsBtn;
	  }

	  public JButton getCartsBtn() {
	    return cartsBtn;
	  }

	  public JButton getOrdersBtn() {
	    return ordersBtn;
	  }

	  public JButton getReviewsBtn() {
	    return reviewsBtn;
	  }

	  public JButton getTicketBtn() {
	    return ticketBtn;
	  }

}
