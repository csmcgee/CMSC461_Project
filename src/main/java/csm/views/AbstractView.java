package csm.views;

import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class AbstractView extends JFrame{
	/**
	 * Default serial
	 */
	private static final long serialVersionUID = 1L;

	public AbstractView(String title){
		super(title);
	}
	
	public void buildView(){
		setVisible(true);
		pack();
		setMinimumSize(getSize());
	}
	
	public void closeView(){
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	protected void addToPanel(JPanel panel, JComponent ... components){
		for(JComponent component: components){
			panel.add(component);
		}
	}
}
