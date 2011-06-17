package gui.generic;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GUIHelper {
	public static JPanel addBorder(JPanel panel, String Name) {
		if(Name != null)
			panel.setBorder(BorderFactory.createCompoundBorder(
    			BorderFactory.createTitledBorder(Name),
    			BorderFactory.createEmptyBorder(5,5,5,5)));
		return panel;
	}
	
	public static JScrollPane addBorder(JScrollPane panel, String Name) {
		if(Name != null)
			panel.setBorder(BorderFactory.createCompoundBorder(
    			BorderFactory.createTitledBorder(Name),
    			BorderFactory.createEmptyBorder(5,5,5,5)));
		return panel;
	}
}
