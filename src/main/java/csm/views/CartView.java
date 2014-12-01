package csm.views;

import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import csm.classes.Student;
import csm.model_views.CartBooksModelView;
import csm.model_views.StudentComboBoxModel;
import csm.models.StudentCartModel;
import csm.utilities.DAO;
import csm.utilities.JTableFactory;
import csm.utilities.SpringUtilities;

public class CartView extends AbstractView{
  final public static String title = "Cart";
  final private JPanel container;

  final private JComboBox<Student> student;
  final private JComboBox<String> formatField;
  final private JTextField isbnField;
  final private JButton searchBtn;
  final private JTable cartItems;
  final private JTextField quantityField;
  
  private final JRadioButton rentRadio;
  private final JRadioButton buyRadio;
  private final ButtonGroup purchaseType;
  
  private final JRadioButton newRadio;
  private final JRadioButton usedRadio;
  private final ButtonGroup bookStatus;
  
  private final JButton removeBtn;
  private final JButton addToCartBtn;
  private final JButton orderBtn;
  
  private final JTextField ccNameField;
  private final JTextField ccNumField;
  private final JTextField ccExpField;
  private final JComboBox<String> ccTypeField;
  private final JComboBox<String> shippingField;
  

  
  public CartView() throws SQLException {
    super(title);
    container = new JPanel(new SpringLayout());
    
    JPanel form = new JPanel(new SpringLayout());
    JPanel bookSearch = new JPanel(new SpringLayout());
    student = new JComboBox<Student>(new StudentComboBoxModel());
    
    isbnField = new JTextField(15);
    searchBtn = new JButton("Find Books");
    addToPanel(bookSearch, new JLabel("ISBN:"), isbnField, searchBtn);
    
    String[] formats = {"Hardcover", "Softcover", "Ebook"};
    formatField = new JComboBox<String>(formats);
    formatField.setMaximumSize(new Dimension(100, 125));
    SpringUtilities.makeCompactGrid(bookSearch, 1, 3, 0, 0, 5, 5);
    
    cartItems = JTableFactory.constructCartJTable(new CartBooksModelView());
    JScrollPane pane = new JScrollPane(cartItems);
    
    rentRadio = new JRadioButton("Rent");
    buyRadio = new JRadioButton("Buy");
    purchaseType = new ButtonGroup();
    purchaseType.add(rentRadio);
    purchaseType.add(buyRadio);
    
    newRadio = new JRadioButton("New");
    usedRadio = new JRadioButton("Used");
    bookStatus = new ButtonGroup();
    bookStatus.add(newRadio);
    bookStatus.add(usedRadio);
    
    quantityField = new JTextField(15);
    
    removeBtn = new JButton("Remove Book");
    addToCartBtn = new JButton("Add To Cart");
    
    orderBtn = new JButton("Order");
    ccNameField = new JTextField(15);
    ccNumField = new JTextField(15);
    ccExpField = new JTextField(15);
    String[] ccTypes = {"VISA", "Mastercard", "American Express"};
    ccTypeField = new JComboBox<String>(ccTypes);
    String[] shippingTypes = {"Same-day", "2nd Business Day", "Standard"};
    shippingField = new JComboBox<String>(shippingTypes);
    JPanel orderBoxes = new JPanel(new SpringLayout());
    addToPanel(orderBoxes, ccTypeField, shippingField);
    SpringUtilities.makeCompactGrid(orderBoxes, 2, 1, 0, 0, 5, 5);
    
    JPanel ccInputsPanel = new JPanel(new SpringLayout());
    addToPanel(ccInputsPanel, new JLabel("Name on Card:"), ccNameField,
        new JLabel("Number:"), ccNumField,
        new JLabel("Expiration Date (YYYY-MM-DD:"), ccExpField);
    SpringUtilities.makeCompactGrid(ccInputsPanel, 3, 2, 0, 0, 5, 5);
    
    
    JPanel qtyPanel = new JPanel(new SpringLayout());
    addToPanel(qtyPanel, new JLabel("Quantity:"), quantityField);
    SpringUtilities.makeCompactGrid(qtyPanel, 1, 2, 0, 0, 5, 5);
    
    JPanel statusPanel = new JPanel(new SpringLayout());
    addToPanel(statusPanel, newRadio, usedRadio);
    SpringUtilities.makeCompactGrid(statusPanel, 2, 1, 0, 0, 5, 5);
    
    JPanel typePanel = new JPanel(new SpringLayout());
    addToPanel(typePanel, rentRadio, buyRadio);
    SpringUtilities.makeCompactGrid(typePanel, 2, 1, 0, 0, 5, 5);
    
    JPanel radiosPanel = new JPanel(new SpringLayout());
    addToPanel(radiosPanel, typePanel, statusPanel, qtyPanel);
    SpringUtilities.makeCompactGrid(radiosPanel, 1, 3, 0, 0, 5, 5);
    
    JPanel cartBtnPanel = new JPanel(new SpringLayout());
    addToPanel(cartBtnPanel, removeBtn, addToCartBtn);
    SpringUtilities.makeGrid(cartBtnPanel, 1, 2, 0, 0, 5, 5);
    
    
    
    addToPanel(form, student, bookSearch, formatField, radiosPanel, 
        cartBtnPanel, orderBtn, ccInputsPanel, orderBoxes);
    SpringUtilities.makeCompactGrid(form, 8, 1, 0, 0, 5, 5);
    
    
    addToPanel(container, form, pane);
    SpringUtilities.makeCompactGrid(container, 1, 2, 5, 5, 5, 5);
    add(container);
    buildView();
  }
  
  public JTable getCartItems() {
    return cartItems;
  }

  public JComboBox<String> getFormat() {
    return formatField;
  }

  public JComboBox<Student> getStudent() {
    return student;
  }

  public JButton getRemoveBtn() {
    return removeBtn;
  }

  public JButton getAddToCartBtn() {
    return addToCartBtn;
  }

  public JButton getOrderBtn() {
    return orderBtn;
  }

  public JTextField getQuantity() {
    return quantityField;
  }

  public JComboBox<String> getFormatField() {
    return formatField;
  }

  public JTextField getIsbnField() {
    return isbnField;
  }

  public JTextField getQuantityField() {
    return quantityField;
  }

  public ButtonGroup getPurchaseType() {
    return purchaseType;
  }

  public ButtonGroup getBookStatus() {
    return bookStatus;
  }

  public JRadioButton getBuyRadio() {
    return buyRadio;
  }

  public JRadioButton getNewRadio() {
    return newRadio;
  }

  public JTextField getCcNameField() {
    return ccNameField;
  }

  public JTextField getCcNumField() {
    return ccNumField;
  }

  public JTextField getCcExpField() {
    return ccExpField;
  }

  public JComboBox<String> getCcTypeField() {
    return ccTypeField;
  }

  public JComboBox<String> getShippingField() {
    return shippingField;
  }

public JButton getSearchBtn() {
	return searchBtn;
}

}
