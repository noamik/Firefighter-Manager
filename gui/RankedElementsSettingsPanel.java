/**
 * 
 */
package gui;

import gui.generic.EditEntryPanel;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import data.GlobalData;
import datascheme.IdStringMatcher;

/**
 * @author noamik
 *
 */
public class RankedElementsSettingsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private EditEntryPanel basePanel = null;  //  @jve:decl-index=0:
	private RankedElemsTable table = null;  //  @jve:decl-index=0:
	private String name = null;


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
	public void setTrainTable(RankedElemsTable tt) {
		this.table = tt;
	}
	/**
	 * @return
	 */
	private JPanel getRankedElementsPane() {
		if(basePanel == null)
			basePanel = initJPanel();
        return basePanel.getPanel();	
	}
	
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private EditEntryPanel initJPanel() {
		if(name.equalsIgnoreCase("Ausbildung"))
			table = new RankedElemsTable(new String[]{"Nr.", name, "Pos", ""}, GlobalData.getInstance().getLehrgänge());
		if(name.equalsIgnoreCase("Dienstgrade"))
			table = new RankedElemsTable(new String[]{"Nr.", name, "Pos", ""}, GlobalData.getInstance().getDienstgrade());
		if(name.equalsIgnoreCase("Status"))
			table = new RankedElemsTable(new String[]{"Nr.", name, "Pos", ""}, GlobalData.getInstance().getMemberStatus());
		return new EditEntryPanel(null,null,table.getTable(),new RankedElementsEditPanel(table.getIdStringMatcher(), name, table));
	}

}
