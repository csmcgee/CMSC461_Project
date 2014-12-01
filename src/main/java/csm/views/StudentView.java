package csm.views;

import java.awt.Dimension;
import java.sql.ResultSet;
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

import csm.models.StudentModel;
import csm.models.UniversityModel;
import csm.utilities.DAO;
import csm.utilities.JTableFactory;
import csm.utilities.SpringUtilities;

public class StudentView extends AbstractView {

  private static final long serialVersionUID = 5129806756362137688L;

  public static String title = "Student";

  private JPanel container;

  private final JTextField firstNameField;
  private final JTextField lastNameField;
  private final JTextField emailField;
  private final JTextField streetNumField;
  private final JTextField streetNameField;
  private final JTextField cityField;
  private final JTextField stateField;
  private final JTextField zipField;
  private final JTextField telephoneField;
  private final JTextField birthDateField;

  private final JComboBox universityBox;
  private final JRadioButton underGradRadio;
  private final JRadioButton graduateRadio;
  private final ButtonGroup studentTier;
  private final JTextField yearField;
  private final JList majorField;

  private final JButton saveBtn;
  private final JButton deleteBtn;
  private final JButton addNewBtn;
  
  private final JTable table;
  
  public JTextField getBirthDateField() {
    return birthDateField;
  }

  public JTextField getYearField(){
    return yearField;
  }
  
  public JRadioButton getUnderGradRadio() {
    return underGradRadio;
  }

  public JTextField getEmailField() {
    return emailField;
  }
  
  public JTextField getFirstNameField() {
    return firstNameField;
  }

  public JTextField getLastNameField() {
    return lastNameField;
  }

  public JButton getSaveBtn() {
    return saveBtn;
  }

  public JButton getDeleteBtn() {
    return deleteBtn;
  }

  public JButton getAddNewBtn() {
    return addNewBtn;
  }
  public JTextField getStreetNumField() {
    return streetNumField;
  }

  public JTextField getStreetNameField() {
    return streetNameField;
  }

  public JTextField getCityField() {
    return cityField;
  }

  public JTextField getZipField() {
    return zipField;
  }

  public JTextField getTelephoneField() {
    return telephoneField;
  }
  
  public JComboBox getUniversityBox() {
    return universityBox;
  }

  public ButtonGroup getStudentTier() {
    return studentTier;
  }
  
  public JTable getTable() {
    return table;
  }
  @SuppressWarnings("unchecked")
  public StudentView() throws SQLException {
    super(title);

    container = new JPanel(new SpringLayout());

    JPanel left = new JPanel(new SpringLayout());
    JPanel btnContainer = new JPanel(new SpringLayout());
    saveBtn = new JButton("Save");
    deleteBtn = new JButton("Delete");
    addNewBtn = new JButton("Create Student");
    addToPanel(btnContainer, addNewBtn, saveBtn, deleteBtn);
    SpringUtilities.makeGrid(btnContainer, 1, 3, 0, 0, 5, 5);

    JPanel form = new JPanel(new SpringLayout());
    firstNameField = new JTextField(15);
    lastNameField = new JTextField(15);
    emailField = new JTextField(15);
    streetNumField = new JTextField(15);
    streetNameField = new JTextField(15);
    cityField = new JTextField(15);
    stateField = new JTextField(15);
    zipField = new JTextField(15);
    telephoneField = new JTextField(15);
    birthDateField = new JTextField(15);
    majorField = new JList<Object>(StudentModel.getAllMajors().toArray());
    
    
    addToPanel(form, new JLabel("First Name:"), firstNameField, 
                     new JLabel("Last Name:"), lastNameField,
                     new JLabel("Birthday(YYYY-MM-DD):"), birthDateField,
                     new JLabel("Email:"), emailField,
                     new JLabel("Street Number:"), streetNumField,
                     new JLabel("Street Name:"), streetNameField,
                     new JLabel("City:"), cityField,
                     new JLabel("State:"), stateField,
                     new JLabel("Zip:"), zipField,
                     new JLabel("Telephone:"),telephoneField,
                     new JLabel("Major(s):"), majorField);
    SpringUtilities.makeCompactGrid(form, 11, 2, 0, 0, 5, 5);
    
    JPanel univForm = new JPanel(new SpringLayout());
    universityBox = new JComboBox(UniversityModel.getUniversities().toArray());
    universityBox.setMaximumSize(new Dimension(15, 15));
    yearField = new JTextField(15);
    yearField.setMaximumSize(new Dimension(15,15));
    addToPanel(univForm, new JLabel("University:"), universityBox,
        new JLabel("Year:"), yearField);
    SpringUtilities.makeGrid(univForm, 2, 2, 0, 0, 5, 5);
    
    underGradRadio = new JRadioButton("Undergraduate");
    graduateRadio = new JRadioButton("Graduate");
    studentTier = new ButtonGroup();
    studentTier.add(underGradRadio);
    studentTier.add(graduateRadio);
    
    
    JPanel univForm2 = new JPanel(new SpringLayout());
    addToPanel(univForm2, underGradRadio, graduateRadio);
    SpringUtilities.makeGrid(univForm2, 2, 1, 0, 0, 5, 5);
    
    JPanel univContainer = new JPanel(new SpringLayout());
    addToPanel(univContainer, univForm, univForm2);
    SpringUtilities.makeGrid(univContainer, 1, 2, 0, 0, 5, 5);
    

    addToPanel(left, form, univContainer, btnContainer);
    SpringUtilities.makeCompactGrid(left, 3, 1, 0, 0, 5, 5);

    DAO db = DAO.getInstance();
    ResultSet rs = null;
    rs = db.selectAllFrom("person natural join student");
    table = JTableFactory.constructStudentJTable(rs);
//    DefaultTableModel model = (DefaultTableModel) table.getModel();
//    model.addRow(new Object[]{"Hello", "Test"});
    
    
    JScrollPane pane = new JScrollPane(table);

    addToPanel(container, left, pane);
    SpringUtilities.makeCompactGrid(container, 1, 2, 5, 5, 5, 5);
    add(container);
    buildView();

  }
  
  public void setFields(ResultSet rs, int[] majorIndices){
    try {
      clearFields();
      if(rs.next()){
        firstNameField.setText(rs.getString(StudentModel.FIRST_NAME));
        lastNameField.setText(rs.getString(StudentModel.LAST_NAME));
        emailField.setText(rs.getString(StudentModel.EMAIL));
        streetNumField.setText(rs.getString(StudentModel.STREET_NUM));
        streetNameField.setText(rs.getString(StudentModel.STREET_NAME));
        stateField.setText(rs.getString(StudentModel.STATE));
        cityField.setText(rs.getString(StudentModel.CITY));
        zipField.setText(rs.getString(StudentModel.ZIP));
        telephoneField.setText(rs.getString(StudentModel.TELEPHONE));
        universityBox.setSelectedIndex(
            UniversityModel.getUniversities()
            .indexOf(UniversityModel
                .getUniversityByID(rs
                    .getInt(StudentModel.UNIVERSITY_ID))));
        if(rs.getString(StudentModel.STATUS).equals("Undergraduate")){
          underGradRadio.setSelected(true);
        }else{
          graduateRadio.setSelected(true);
        }
        yearField.setText(rs.getString(StudentModel.YEAR));
        majorField.setSelectedIndices(majorIndices);
        birthDateField.setText(rs.getString(StudentModel.BIRTH_DATE));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  

  public StudentView clearFields(){
    firstNameField.setText("");
    lastNameField.setText("");
    emailField.setText("");
    birthDateField.setText("");
    streetNumField.setText("");
    streetNameField.setText("");
    cityField.setText("");
    stateField.setText("");
    zipField.setText("");
    telephoneField.setText("");
    universityBox.setSelectedIndex(-1);
    yearField.setText("");
    majorField.clearSelection();
    return this;
  }

  public JList getMajorField() {
    return majorField;
  }
  
  public JTextField getStateField() {
    return stateField;
  }

}
