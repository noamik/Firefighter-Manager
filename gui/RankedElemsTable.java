/**
 * 
 */
package gui;

import gui.generic.ButtonRenderer;
import gui.generic.GenericTable;
import gui.generic.RowIndexRenderer;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import data.GlobalData;
import datascheme.IdStringMatcher;
import datascheme.RankedElement;

/**
 * @author noamik
 *
 */
public class RankedElemsTable implements TableModelListener {
	
	private JTable rankedElemsTable	= null;
	private GenericTable gmt 		= null;
	private String[] titles 		= null;
	private Object[][] data 		= null;
	private IdStringMatcher ism     = null;

	/**
	 * 
	 */
	public RankedElemsTable(String[] titles, IdStringMatcher tism) {
		this.titles = titles;
		this.ism = tism;
		initialize();
	}
	
	public IdStringMatcher getIdStringMatcher() {
		return ism;
	}
	
	public Integer getMaxId() {
		if(data == null)
			return -1;
		Integer max = -1;
		for(int i=0;i<data.length;i++) {
			System.out.println("Current max: " + max + " checking now: " + data[i][titles.length-1]);
//			if(data[i][titles.length-1].getClass().isInstance(new Integer()))
			if((Integer)data[i][titles.length-1]>max)
				max = (Integer)data[i][titles.length-1];
		}
		return max;
	}
	
	private void initialize() {
//		System.out.println("MemberTable-GD-toString:" +gd.toString());
		data   = createTableArray(ism);
		gmt    = new GenericTable(titles,data);
		gmt.setColumnEditable(0, false);
		rankedElemsTable = new JTable(gmt);
		rankedElemsTable.setPreferredScrollableViewportSize(new Dimension(155, 100));
		rankedElemsTable.setFillsViewportHeight(true);
		rankedElemsTable.setAutoCreateRowSorter(true);
        replaceColumnWithButton(rankedElemsTable.getColumnModel().getColumn(3));
        replaceFirstColumnCellRenderer(rankedElemsTable.getColumnModel().getColumn(0));
        rankedElemsTable.getColumnModel().getColumn(0).setMaxWidth(25);
        rankedElemsTable.getColumnModel().getColumn(2).setMaxWidth(35);
        rankedElemsTable.getColumnModel().getColumn(3).setMaxWidth(25);
        rankedElemsTable.getModel().addTableModelListener(this);
	}
	
	/**
	 * @return
	 */
	public String[] getTitles() {
		return titles;
	}
	
	/**
	 * @return
	 */
	public IdStringMatcher getISM() {
		return ism;
	}
	
	/**
	 * @return
	 */
	public JTable getTable() {
		return this.rankedElemsTable;
	}
	
	/**
	 * @return
	 */
	public GenericTable getGenericTable() {
		return gmt;
	}

	public Object[][] createTableArray(IdStringMatcher tism) {
		ism = tism;
		HashMap<Integer,RankedElement> localMap = tism.getElements();
		if(localMap.size() == 0)
			return null;
		Object[][] TableObject = new Object[localMap.size()][titles.length];
		Iterator<Entry<Integer, RankedElement>> it = localMap.entrySet().iterator();
		RankedElement temp;
		for(int i=0;i<localMap.size();i++) {
			Entry<?, ?> te = it.next();
			temp = (RankedElement) te.getValue();
			Integer id = (Integer) te.getKey(); 
//			System.out.println(temp.toString());
			TableObject[i][0] = i;
			TableObject[i][1] = temp.getName();
			TableObject[i][2] = temp.getPos();
			TableObject[i][3] = id;
		}
		return TableObject;
	}

	private void replaceFirstColumnCellRenderer(TableColumn dgc) {
		dgc.setCellRenderer(new RowIndexRenderer());
	}
	
	/**
	 * @param data
	 */
	public void updateTableData(Object[][] data) {
		this.data = data;
		gmt.setData(data);
		gmt.setColumnEditable(0, false);
		this.getGenericTable().fireTableDataChanged();
	}
	
	/**
	 * @param elems
	 */
	public void updateMemberTableData(IdStringMatcher elems) {
		ism = elems;
		Object[][] data = createTableArray(elems);
		updateTableData(data);
	}

	private void replaceColumnWithButton(TableColumn dgc) {
		dgc.setCellRenderer(new ButtonRenderer());
	    dgc.setCellEditor(new ISMButtonEditor(new JCheckBox(), ism, titles[1]));
	}

	@Override
	public void tableChanged(TableModelEvent arg0) {
		Integer col = arg0.getColumn();
		System.out.println("Table changed: FirstRow:"+arg0.getFirstRow() + " Col:"+col);
		if(col != -1 && col != 3) {
			Integer id = (Integer) gmt.getValueAt(arg0.getFirstRow(), gmt.getColumnCount()-1);
			RankedElement elem = ism.getElement(id);
			Object item = gmt.getValueAt(arg0.getFirstRow(), col);
			switch(col) {
				case 1: elem.setName((String)item); break;
				case 2: elem.setPos(Integer.parseInt((String)item)); break;
			}
			ism.addElement(id, elem);
			if(titles[1].equalsIgnoreCase("Ausbildung"))
      			GlobalData.getInstance().addTraining(id,elem);
      		if(titles[1].equalsIgnoreCase("Dienstgrade"))
      			GlobalData.getInstance().addRank(id,elem);
      		if(titles[1].equalsIgnoreCase("Status"))
      			GlobalData.getInstance().addStatus(id,elem);
		}
//		printTableToConsole();
	}
	
	/**
	 * 
	 */
	public void printTableToConsole() {
		System.out.println("------------------------------------");
		for(int i=0; i<rankedElemsTable.getModel().getRowCount();i++) {
			System.out.print("|");
			for(int j=0;j<rankedElemsTable.getModel().getColumnCount();j++)
				System.out.print(rankedElemsTable.getModel().getValueAt(i, j)+"|");
			System.out.println("");
		}
		System.out.println("------------------------------------");
	}
}
