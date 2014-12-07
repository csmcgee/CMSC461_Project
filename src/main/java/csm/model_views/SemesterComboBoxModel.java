package csm.model_views;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

public class SemesterComboBoxModel implements ComboBoxModel<String>{
	String selected;
	String[] semesters = {"Fall", "Spring", "Summer", "Winter"};
	
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	public String getElementAt(int arg0) {
		return semesters[arg0];
	}

	public int getSize() {
		return semesters.length;
	}

	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	public Object getSelectedItem() {
		return selected;
	}

	public void setSelectedItem(Object anItem) {
		selected = (String) anItem;
		
	}

}
