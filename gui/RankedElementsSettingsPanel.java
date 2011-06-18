/**
 * 
 */
package gui;

import gui.generic.EditEntryPanel;
import gui.generic.GUIHelper;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import data.GlobalData;

/**
 * @author noamik
 *
 */
public class RankedElementsSettingsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
//	private EditEntryPanel basePanel = null;
	private RankedElemsTable table = null;
	private String name = null;
	
	private JTable editableTable 		= null;
	private JPanel basePanel 			= null;
	private JPanel editPanel 			= null;
	private JScrollPane tableScroller 	= null;


	/**
	 * This is the default constructor
	 */
	public RankedElementsSettingsPanel(String tname) {
		super();
		name = tname;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new GridLayout(0,1));
		this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(name),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		
		this.add(getRankedElementsPane());
	}

	/**
	 * @return the table
	 */
	public RankedElemsTable getRankedElemTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(RankedElemsTable tt) {
		this.table = tt;
	}
	/**
	 * @return
	 */
	private JPanel getRankedElementsPane() {
		if(basePanel == null)
			basePanel = createBasePanel();
        return basePanel;	
	}
	
	/**
	 * @return
	 */
	private JPanel createBasePanel() {
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
		basePanel 		= GUIHelper.addBorder(basePanel, null);
		if(name.equalsIgnoreCase("Ausbildung"))
			table = new RankedElemsTable(new String[]{"Nr.", name, "Pos", ""}, GlobalData.getInstance().getLehrgänge());
		if(name.equalsIgnoreCase("Dienstgrade"))
			table = new RankedElemsTable(new String[]{"Nr.", name, "Pos", ""}, GlobalData.getInstance().getDienstgrade());
		if(name.equalsIgnoreCase("Status"))
			table = new RankedElemsTable(new String[]{"Nr.", name, "Pos", ""}, GlobalData.getInstance().getMemberStatus());
		editableTable 	= table.getTable();
		editPanel 		= new RankedElementsEditPanel(table.getIdStringMatcher(), name, table);
		tableScroller 	= new JScrollPane(editableTable);
		tableScroller   = GUIHelper.addBorder(tableScroller, null);
		basePanel.add(tableScroller, tableConstr);
		basePanel.add(editPanel, editPanelConstr);
		return basePanel;
	}

}
