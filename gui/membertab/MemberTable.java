package gui.membertab;

import gui.generic.ButtonRenderer;
import gui.generic.GenericTable;
import gui.generic.RowIndexRenderer;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;


import util.util;

import data.GlobalData;
import datascheme.Mitglied;
import datascheme.RankedElement;

public class MemberTable implements TableModelListener {
	private JTable memberTable		= null;
	private GenericTable gmt 		= null;
	private String[] titles 		= null;
	private Object[][] data 		= null;
	private GlobalData gd 			= null;
	private JComboBox Dienstgrade 	= null;
	private JComboBox Status      	= null;
	
	/**
	 * 
	 */
	public MemberTable() {
		initialize();
	}
	
	/**
	 * @return
	 */
	public Object[][] getData() {
		return data;
	}
	
	private void initialize() {
		if(util.DebugMemberPane)
			System.out.println("Initializing Member Table");
		gd     = GlobalData.getInstance();
//		System.out.println("MemberTable-GD-toString:" +gd.toString());
		titles = new String[]{"Nr.", "Name", "Vorname", "Dienstgrad", "Geburtsdatum", "Aufnahmedatum", "Status", ""};
		data   = createMemberTableArray(gd.getMitglieder());
		gmt    = new GenericTable(titles,data);
		gmt.setColumnEditable(0, false);
		memberTable = new JTable(gmt);
		memberTable.setPreferredScrollableViewportSize(new Dimension(740, 450));
        memberTable.setFillsViewportHeight(true);
        memberTable.setAutoCreateRowSorter(true);
        memberTable.getColumnModel().getColumn(0).setMaxWidth(25);
        memberTable.getColumnModel().getColumn(7).setMaxWidth(25);
        replaceColumnWithComboBox(memberTable.getColumnModel().getColumn(3),Dienstgrade);
        replaceColumnWithComboBox(memberTable.getColumnModel().getColumn(6),Status);
        replaceColumnWithButton(memberTable.getColumnModel().getColumn(7));
        replaceFirstColumnCellRenderer(memberTable.getColumnModel().getColumn(0));
        memberTable.getModel().addTableModelListener(this);
	}

	/**
	 * @param data
	 */
	public void updateMemberTableData(Object[][] data) {
		this.data = data;
		gmt.setData(data);
		gmt.setColumnEditable(0, false);
		this.getGenericTable().fireTableDataChanged();
	}
	
	/**
	 * @param Mitglieder
	 */
	public void updateMemberTableData(HashMap<Integer,Mitglied> Mitglieder) {
		data = createMemberTableArray(Mitglieder);
		gmt.setData(data);
		gmt.setColumnEditable(0, false);
		getGenericTable().fireTableDataChanged();
	}
	
	/**
	 * @param Mitglieder
	 * @return
	 */
	public Object[][] createMemberTableArray(HashMap<Integer,Mitglied> Mitglieder) {
		if(util.DebugMemberPane)
			System.out.println("Creating Member Table Array");
		if(Mitglieder == null) {
			if(util.DebugMemberPane)
				System.out.println("Member Map null");
			return null;
		}
		if(Mitglieder.size() == 0) {
			if(util.DebugMemberPane)
				System.out.println("Member Map empty");
			return null;
		}
		updateDienstgradeComboBox();
		updateStatusComboBox();
		Object[][] MemberTableObject = new Object[Mitglieder.size()][titles.length];
		Iterator<Entry<Integer, Mitglied>> it = Mitglieder.entrySet().iterator();
		Mitglied temp;
		for(int i=0;i<Mitglieder.size();i++) {
			temp = it.next().getValue();
//			System.out.println(temp.toString());
			MemberTableObject[i][0] = i;
			MemberTableObject[i][1] = temp.getName();
			MemberTableObject[i][2] = temp.getVorname();
			MemberTableObject[i][3] = gd.getDienstgrade().getElementString(temp.getDienstgradId());
			MemberTableObject[i][4] = util.DateToString(temp.getGeburtsdatum());
			MemberTableObject[i][5] = util.DateToString(temp.getAufnahmedatum());
			MemberTableObject[i][6] = gd.getMemberStatus().getElementString(temp.getStatus());
			MemberTableObject[i][7] = temp.getId();
		}
		return MemberTableObject;
	}
	
	private void replaceColumnWithComboBox(TableColumn dgc, JComboBox jcb) {
		dgc.setCellEditor(new DefaultCellEditor(jcb));
        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        dgc.setCellRenderer(renderer);
	}
	
	private void replaceColumnWithButton(TableColumn dgc) {
		dgc.setCellRenderer(new ButtonRenderer());
	    dgc.setCellEditor(new MemberButtonEditor(new JCheckBox()));
	}
	
	private void replaceFirstColumnCellRenderer(TableColumn dgc) {
		dgc.setCellRenderer(new RowIndexRenderer());
	}
	
	private void updateDienstgradeComboBox() {
		Iterator<RankedElement> it = gd.getDienstgrade().getElements().values().iterator();
		Dienstgrade = new JComboBox();
		while(it.hasNext())
			Dienstgrade.addItem(it.next().getName());
	}
	
	private void updateStatusComboBox() {
		Iterator<RankedElement> it = gd.getMemberStatus().getElements().values().iterator();
		Status = new JComboBox();
		while(it.hasNext())
			Status.addItem(it.next().getName());
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
	public JTable getTable() {
		return memberTable;
	}
	
	/**
	 * @return
	 */
	public GenericTable getGenericTable() {
		return gmt;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.TableModelListener#tableChanged(javax.swing.event.TableModelEvent)
	 */
	@Override
	public void tableChanged(TableModelEvent arg0) {
		Integer col = arg0.getColumn();
//		System.out.println("Table changed: FirstRow:"+arg0.getFirstRow() + " Col:"+col);
		if(col != -1 && col != 7) {
			Mitglied member = GlobalData.getInstance().getMitglieder().get(gmt.getValueAt(arg0.getFirstRow(), gmt.getColumnCount()-1));
			Object item = gmt.getValueAt(arg0.getFirstRow(), col);
			switch(col) {
				case 1: member.setName((String)item); break;
				case 2: member.setVorname((String)item); break;
				case 3: member.setDienstgrad(GlobalData.getInstance().getDienstgrade().getId((String)item)); break;
				case 4: member.setGeburtsdatum(util.StringToDate((String)item)); break;
				case 5: member.setAufnahmedatum(util.StringToDate((String)item)); break;
				case 6: member.setStatus(GlobalData.getInstance().getMemberStatus().getId((String)item)); break;
			}
			GlobalData.getInstance().addMember(member);
		}
//		printTableToConsole();
	}
	
	/**
	 * 
	 */
	public void printTableToConsole() {
		System.out.println("------------------------------------");
		for(int i=0; i<memberTable.getModel().getRowCount();i++) {
			System.out.print("|");
			for(int j=0;j<memberTable.getModel().getColumnCount();j++)
				System.out.print(memberTable.getModel().getValueAt(i, j)+"|");
			System.out.println("");
		}
		System.out.println("------------------------------------");
	}

}
