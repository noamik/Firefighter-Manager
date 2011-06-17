package gui.generic;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RowIndexRenderer extends JLabel implements TableCellRenderer {
	
	    public Component getTableCellRendererComponent(JTable table, Object color,
	        boolean isSelected, boolean hasFocus, int row, int column) {
	        setText(Integer.toString(row));
	        return this;
	    }
}
