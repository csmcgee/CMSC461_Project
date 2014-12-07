package csm.views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import csm.classes.Course;
import csm.classes.University;
import csm.model_views.RequiredBooksModelView;
import csm.model_views.SemesterComboBoxModel;
import csm.model_views.UniversityComboBoxModel;
import csm.utilities.SpringUtilities;

public class RequiredBooksView extends AbstractView {

	private static String title = "Book Search";
	private final JComboBox<University> universities;
	private final JPanel container;
	private final JComboBox<Course> courses;
	private final JComboBox<String> semesters;
	private final JTextField sectionId;
	private final JButton searchBtn;
	private final JTable booksTable;
	private final JTextField year;
//	private final JComboBox sections;
	
	public RequiredBooksView(int studentId) {
		super(title);
		container = new JPanel(new SpringLayout());
		
		JPanel form = new JPanel(new SpringLayout());
		JPanel left = new JPanel(new SpringLayout());
		universities = new JComboBox<University>(new UniversityComboBoxModel());
		addToPanel(form, new JLabel("University:"), universities);
		courses = new JComboBox();
		addToPanel(form, new JLabel("Course:"), courses);
		
		semesters = new JComboBox(new SemesterComboBoxModel());
		addToPanel(form, new JLabel("Semester:"), semesters);
		
		sectionId = new JTextField(5);
		addToPanel(form, new JLabel("Section Id:"), sectionId);
		
		year = new JTextField(5);
		addToPanel(form, new JLabel("Year:"), year);
		
		SpringUtilities.makeCompactGrid(form, 5, 2, 5, 5, 5, 5);
		searchBtn = new JButton("Search");
		
		addToPanel(left, form, searchBtn);
		SpringUtilities.makeCompactGrid(left, 2, 1, 5, 5, 5, 5);
		
		booksTable = new JTable();
		JScrollPane pane = new JScrollPane(booksTable);
		
		addToPanel(container, left, pane);
		SpringUtilities.makeCompactGrid(container, 1, 2, 0, 0, 5, 5);
		
		add(container);
		buildView();
	}

	public JComboBox<University> getUniversities() {
		return universities;
	}

	public JComboBox getCourses() {
		return courses;
	}
//
//	public JComboBox getSections() {
//		return sections;
//	}

	public JComboBox<String> getSemesters() {
		return semesters;
	}

	public JButton getSearchBtn() {
		return searchBtn;
	}

	public JTextField getSectionId() {
		return sectionId;
	}

	public JTable getBooksTable() {
		return booksTable;
	}

	public JTextField getYear() {
		return year;
	}
	
}
