/**
 * 
 */
package gui.generic;

import java.util.HashMap;
import javax.swing.table.TableModel;

/**
 * @author noamik
 * Any class implementing this interface should extend AbstractTableModel,
 * where TableData receives changes, it should fire fireTableDataChanged();
 */
public interface GenericTableInterface extends TableModel {
	
	/**
	 * This method should convert the HashMap of data-items into an Object[][]
	 * @return
	 */
	public Object[][] 	getData();
	
	/**
	 * This methods returns the Title[] of this table
	 * @return
	 */
	public String[] 	getTitles();
	
	/**
	 * setData has to (convert data to) update our table
	 * it has to call fireTableDataChanged();
	 * @param data
	 */
	public void 		setData(HashMap<Integer,Object> data);
	
	/**
	 * @param titles
	 */
	public void 		setTitles(String[] titles);
	
	/**
	 * addItem has to (convert data to) update our table
	 * it has to call fireTableDataChanged();
	 * @param Id
	 * @param dataObject
	 */
	public void 		addItem(Integer Id, Object dataObject);
	
	/**
	 * @return
	 */
	public int 			getColumnCount();
	
	/**
	 * @return
	 */
	public int 			getRowCount();
	
	/**
	 * @param col
	 * @return
	 */
	public String 		getColumnName(int col);
	
	/**
	 * @param row
	 * @param col
	 * @return
	 */
	public Object 		getValueAt(int row, int col);
	
	/**
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean 		isCellEditable(int row, int col);
	
	/**
	 * has to call fireTableDataChanged();
	 * @param row
	 * @param col
	 * @param edit
	 */
	public void 		setCellEditable(int row, int col, Boolean edit);
	
	/**
	 * has to call fireTableDataChanged();
	 * @param colNumber
	 * @param init
	 */
	public void 		setColumnEditable(int colNumber, Boolean init);
	
	/**
	 * has to call fireTableDataChanged();
	 * @param rowNumber
	 * @param init
	 */
	public void 		setRowEditable(int rowNumber, Boolean init);
	
}
