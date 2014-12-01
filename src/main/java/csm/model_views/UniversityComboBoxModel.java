package csm.model_views;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import csm.classes.University;
import csm.models.UniversityModel;

public class UniversityComboBoxModel implements ComboBoxModel<University>{
	private University selected;
	private ArrayList<University> universities;
	public UniversityComboBoxModel(){
		universities = UniversityModel.getUniversityList();
	}
	
	public void addListDataListener(ListDataListener l) {
	}

	public University getElementAt(int index) {
		return universities.get(index);
	}

	public int getSize() {
		return universities.size();
	}

	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub	
	}

	public Object getSelectedItem() {
		return selected;
	}

	public void setSelectedItem(Object arg0) {
		selected = (University) arg0;
	}

}
