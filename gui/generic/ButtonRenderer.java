/**
 * 
 */
package gui.generic;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 * @author noamik
 *
 */
public class ButtonRenderer extends JButton implements TableCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7250530193741687777L;
	

	public ButtonRenderer() {
	    setOpaque(true);
	  }
	  
	  public Component getTableCellRendererComponent(JTable table, Object value,
	                   boolean isSelected, boolean hasFocus, int row, int column) {
	    if (isSelected) {
	      setForeground(table.getSelectionForeground());
	      setBackground(table.getSelectionBackground());
	    } else{
	      setForeground(table.getForeground());
	      setBackground(UIManager.getColor("Button.background"));
	    }
	    setText( "X"/*(value ==null) ? "" : value.toString()*/ );
	    setToolTipText("Click löscht Eintrag");
	    return this;
	  }
}
