package gui.generic;

import javax.swing.table.AbstractTableModel;


@SuppressWarnings("serial")
public class GenericTable extends AbstractTableModel {
	
	private String[] columnNames;
	private Object[][] data;
	private Boolean[][] editable;
	/**
	 * @return the data
	 */
	public Object[][] getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object[][] data) {
		this.data = data;
		editable = new Boolean[getRowCount()][getColumnCount()];
		for(int i=0;i<data.length;i++)
			setRowEditable(i,Boolean.TRUE);
	}
	
	public GenericTable(String[] titles, Object[][] data) {
		this.columnNames = titles;
		this.data = data;
		if(data == null || titles == null)
			return;
		this.editable = new Boolean[data.length][titles.length];
		for(int i=0; i<editable.length;i++)
			setRowEditable(i,Boolean.TRUE);
	}
	
	public void addRow(Object[] newRow) {
		Object[][] newdata = new Object[getRowCount()+1][getColumnCount()];
		if(data != null)
			for(int i=0;i<data.length;i++)
				newdata[i] = data[i];
		newdata[newdata.length-1] = newRow;
		data = newdata;
		Boolean[][] newBool = new Boolean[getRowCount()+1][getColumnCount()];
		if(editable != null)
			for(int i=0;i<data.length;i++)
				newBool[i] = editable[i];
		editable = newBool;
		setRowEditable(editable.length-1,Boolean.TRUE);
		fireTableDataChanged();
	}
	
	public void setRowEditable(int rowNumber, Boolean init) {
		for(int i=0; i<editable[rowNumber].length;i++)
			editable[rowNumber][i] = init;
	}
	
	public void setColumnEditable(int colNumber, Boolean init) {
		if(editable == null)
			return;
		for(int i=0; i<editable.length;i++)
			editable[i][colNumber] = init;
	}

	public int getColumnCount() {
		if(columnNames == null)
			return 0;
		return columnNames.length;
	}

	public int getRowCount() {
		if(data == null)
			return 0;
		return data.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		if(row == -1 || col == -1)
			return null;
		if(row>getRowCount())
			return null;
		if(col>getColumnCount())
			return null;
		return data[row][col];
	}

	/*
	 * JTable uses this method to determine the default renderer/
	 * editor for each cell.  If we didn't implement this method,
	 * then the last column would contain text ("true"/"false"),
	 * rather than a check box.
	 */
	@SuppressWarnings("unchecked")
	public Class getColumnClass(int c) {
		if(getValueAt(0, c) == null)
			return (new String()).getClass();
		return getValueAt(0, c).getClass();
	}

	/*
	 * Don't need to implement this method unless your table's
	 * editable.
	 */
	public boolean isCellEditable(int row, int col) {
		//Note that the data/cell address is constant,
		//no matter where the cell appears onscreen.
//		System.out.println("Row: " + row + " Col:" + col);
		return editable[row][col];
//		return true;
	}
	
	/*
	 * Don't need to implement this method unless your table's
	 * editable.
	 */
	public void setCellEditable(int row, int col, Boolean edit) {
		//Note that the data/cell address is constant,
		//no matter where the cell appears onscreen.
		editable[row][col] = edit;
	}

	/*
	 * Don't need to implement this method unless your table's
	 * data can change.
	 */
	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	@SuppressWarnings("unused")
	private void printDebugData() {
		int numRows = getRowCount();
		int numCols = getColumnCount();

		for (int i=0; i < numRows; i++) {
			System.out.print("    row " + i + ":");
			for (int j=0; j < numCols; j++) {
				System.out.print("  " + data[i][j]);
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}

}
