package csm.model_views;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import csm.classes.Course;
import csm.models.UniversityModel;

public class CourseComboBoxModel implements ComboBoxModel<Course>{
	private Course selected;
	private ArrayList<Course> courses;
	
	public CourseComboBoxModel(int university_id){
		super();
		courses = UniversityModel.getUniversityCourseList(university_id);
	}
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	public Course getElementAt(int arg0) {
		return courses.get(arg0);
	}

	public int getSize() {
		return courses.size();
	}

	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	public Object getSelectedItem() {
		return selected;
	}

	public void setSelectedItem(Object arg0) {
		selected = (Course) arg0;
	}
	

}
