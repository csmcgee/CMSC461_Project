package csm.views;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import csm.classes.University;
import csm.model_views.UniversityComboBoxModel;
import csm.utilities.SpringUtilities;

public class RequiredBooksView extends AbstractView {

	private static String title = "Book Search";
	private final JComboBox<University> universities;
	private final JPanel container;
//	private final JComboBox courses;
//	private final JComboBox sections;
	
	public RequiredBooksView(int studentId) {
		super(title);
		container = new JPanel(new SpringLayout());
		universities = new JComboBox<University>(new UniversityComboBoxModel());
		addToPanel(container, new JLabel("University:"), universities);
		SpringUtilities.makeCompactGrid(container, 1, 2, 0, 0, 5, 5);
		add(container);
		buildView();
	}

	public JComboBox<University> getUniversities() {
		return universities;
	}

//	public JComboBox getCourses() {
//		return courses;
//	}
//
//	public JComboBox getSections() {
//		return sections;
//	}
	
}
