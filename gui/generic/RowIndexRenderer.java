package gui.generic;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RowIndexRenderer extends JLabel implements TableCellRenderer {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5123952323306117341L;

		public Component getTableCellRendererComponent(JTable table, Object color,
	        boolean isSelected, boolean hasFocus, int row, int column) {
	        setText(Integer.toString(row));
	        return this;
	    }
}
