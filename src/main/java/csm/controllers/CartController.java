package csm.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import csm.classes.Book;
import csm.classes.CreditCard;
import csm.classes.Student;
import csm.model_views.CartBooksModelView;
import csm.models.StudentCartModel;
import csm.views.AbstractView;
import csm.views.CartView;

public class CartController extends AbstractController {
	private final CartView view;
	private CartBooksModelView cartBooksModel;

	public CartController() throws SQLException {
		view = new CartView();
		setActions();
	}

	private void setActions() {
		view.getStudent().addActionListener(studentBoxEvent());
		view.getRemoveBtn().addActionListener(removeEvent());
		view.getAddToCartBtn().addActionListener(addToCartEvent());
		view.getOrderBtn().addActionListener(orderBtnEvent());
		view.getSearchBtn().addActionListener(searchEvent());
	}

	private ActionListener searchEvent() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tearDownView();
				Student student = (Student) view.getStudent().getSelectedItem();
				RequiredBooksController controller = new RequiredBooksController(
						student.id);

				/**
				 * Set close event to re-open main menu.
				 */
				controller.setWindowCloseEvent(new WindowAdapter() {
					public void windowClosing(WindowEvent event) {
						setUpView();
					}
				});

			}
		};
	}

	private ActionListener orderBtnEvent() {
		return new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Student student = (Student) view.getStudent().getSelectedItem();
				CreditCard cc = constructCCFromInputs();
				ArrayList<Book> books = cartBooksModel.getCartBooks();
				double total = cartBooksModel.calculateTotal(books);
				String orderType = (String) view.getShippingField()
						.getSelectedItem();
				try {
					StudentCartModel.createOrderFromBooks(student.id, books,
							cc, orderType, total);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(view,
							"Oops. Something went wrong with the order.");
				}
				view.repaint();
				JOptionPane.showMessageDialog(view, "Order Successful.");

			}
		};
	}

	private ActionListener addToCartEvent() {
		return new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Student student = (Student) view.getStudent().getSelectedItem();
				Book book = constructBookFromInputs();
				try {
					StudentCartModel.addToCart(student.id, book.ISBN,
							book.type, book.status, book.format, book.quantity);
				} catch (SQLException e) {
					JOptionPane
							.showMessageDialog(
									view,
									"Book does not exist. Please check that all inputs correspond to a book in the inventory.");
					e.printStackTrace();
				}
				view.repaint();

			}

		};
	}

	private ActionListener studentBoxEvent() {
		return new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Student student = (Student) view.getStudent().getSelectedItem();
				CartBooksModelView model = new CartBooksModelView();
				model.setStudentId(student.id);
				cartBooksModel = model;
				view.getCartItems().setModel(model);
				view.repaint();
			}

		};
	}

	private ActionListener removeEvent() {
		return new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Student student = (Student) view.getStudent().getSelectedItem();
				int row = view.getCartItems().getSelectedRow();
				Book book = cartBooksModel.getRow(row);
				try {
					StudentCartModel.removeBookFromCart(student.id, book.ISBN,
							book.type, book.status, book.format);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				view.repaint();

			}
		};
	}

	private CreditCard constructCCFromInputs() {
		String name = view.getCcNameField().getText();
		String num = view.getCcNumField().getText();
		String exp = view.getCcExpField().getText();
		String type = (String) view.getCcTypeField().getSelectedItem();
		return new CreditCard(num, name, exp, type);
	}

	private Book constructBookFromInputs() {
		int quantity = Integer.parseInt(view.getQuantity().getText());
		String ISBN = view.getIsbnField().getText();
		String type = (view.getBuyRadio().isSelected()) ? "Buy" : "Rent";
		String status = (view.getNewRadio().isSelected()) ? "New" : "Used";
		String format = (String) view.getFormatField().getSelectedItem();
		return new Book(quantity, ISBN, type, status, format, 0.0);
	}

	@Override
	public AbstractView getView() {
		return view;
	}

}
