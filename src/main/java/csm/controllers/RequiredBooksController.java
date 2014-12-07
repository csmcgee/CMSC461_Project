package csm.controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import csm.classes.Course;
import csm.classes.University;
import csm.model_views.CourseComboBoxModel;
import csm.model_views.RequiredBooksModelView;
import csm.views.AbstractView;
import csm.views.RequiredBooksView;

public class RequiredBooksController extends AbstractController{
	private int studentId;
	private final RequiredBooksView view;
	
	public RequiredBooksController(int studentId){
		this.studentId = studentId;
		view = new RequiredBooksView(studentId);
		setEvents();
	}
	
	private void setEvents(){
		view.getUniversities().addActionListener(universitySelectionEvent());
		view.getSearchBtn().addActionListener(searchEvent());
	}
	
	public ActionListener universitySelectionEvent(){
		return new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				University university = (University) view.getUniversities().getSelectedItem();
				view.getCourses().setModel(new CourseComboBoxModel(university.id));
				view.repaint();
			}
			
		};
	}
	
	public ActionListener searchEvent(){
		return new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				University university = (University) view.getUniversities().getSelectedItem();
				Course course = (Course) view.getCourses().getSelectedItem();
				int semesterId = view.getSemesters().getSelectedIndex() + 1;
				int sectionId = Integer.parseInt(view.getSectionId().getText());
				int year = Integer.parseInt(view.getYear().getText());
				
				RequiredBooksModelView model = new RequiredBooksModelView(university.id, sectionId, semesterId, course.id, year);
				view.getBooksTable().setModel(model);
				view.repaint();
				
			}
			
		};
	}

	@Override
	public AbstractView getView() {
		// TODO Auto-generated method stub
		return view;
	}
}
