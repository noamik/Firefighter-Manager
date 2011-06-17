package gui.generic;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class EditEntryPanel {
	private JTable editableTable 		= null;
	private JPanel basePanel 			= null;
	private JPanel editPanel 			= null;
	private JScrollPane tableScroller 	= null;
	
	private String Name;
	private JTable table;
	private JPanel edit;
	
//	private LinkedList
	
	/**
	 * @param Name
	 * @param table
	 * @param edit
	 */
	public EditEntryPanel(String Name, JTable table, JPanel edit) {
		this.Name 	= Name;
		this.table 	= table;
		this.edit 	= edit;
		initialize();
	}
	
	private void initialize() {
		GridBagConstraints tableConstr = new GridBagConstraints();
		tableConstr.fill 	= GridBagConstraints.BOTH;
		tableConstr.gridy 	= 0;
		tableConstr.weightx = 1.0;
		tableConstr.weighty = 1.0;
		tableConstr.gridx 	= 0;
		GridBagConstraints editPanelConstr = new GridBagConstraints();
		editPanelConstr.fill 	= GridBagConstraints.HORIZONTAL;
		editPanelConstr.gridy 	= 1;
		editPanelConstr.weightx = 1.0;
		editPanelConstr.weighty = 0.0;
		editPanelConstr.gridx 	= 0;
		basePanel 		= new JPanel();
		basePanel.setLayout(new GridLayout(0,1));
		basePanel.setLayout(new GridBagLayout());
		basePanel 		= GUIHelper.addBorder(basePanel, Name);
		editableTable 	= table;
		editPanel 		= edit;
		tableScroller 	= new JScrollPane(editableTable);
		tableScroller   = GUIHelper.addBorder(tableScroller, "Mitgliedertabelle");
		basePanel.add(tableScroller, tableConstr);
		basePanel.add(editPanel, editPanelConstr);
	}
	
	/**
	 * @return
	 */
	public JPanel getPanel() {
		return basePanel;
	}
	
	
	
	
}
