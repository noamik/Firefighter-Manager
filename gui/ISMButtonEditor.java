package gui;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import data.GlobalData;
import datascheme.IdStringMatcher;

public class ISMButtonEditor extends DefaultCellEditor {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 6520552525683328694L;

	protected JButton button;
	  private String    label;
	  private boolean   isPushed;
	  private IdStringMatcher ism;
	  private String name;

	  public ISMButtonEditor(JCheckBox checkBox, IdStringMatcher tism, String tname) {
	    super(checkBox);
	    ism = tism;
	    name = tname;
	    button = new JButton();
	    initialize();
	  }
	  
	  private void initialize() {
		  button.setOpaque(true);
		  button.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
		    	  Integer id = Integer.parseInt(label);
		    	  int n = JOptionPane.showConfirmDialog(MainApplicationWindow.getMainWindow().getJFrame(),
			    		  ism.getElement(id).toString(),
		                  "Wirklich löschen?",
		                  JOptionPane.OK_CANCEL_OPTION,
		                  JOptionPane.WARNING_MESSAGE);
		          if (n == JOptionPane.YES_OPTION) {
		        	if(name.equalsIgnoreCase("Ausbildung"))
		      			GlobalData.getInstance().removeTraining(id);
		      		if(name.equalsIgnoreCase("Dienstgrade"))
		      			GlobalData.getInstance().removeRank(id);
		      		if(name.equalsIgnoreCase("Status"))
		      			GlobalData.getInstance().removeStatus(id);
//		        	  ism.removeElement(Integer.parseInt(label));
//		        	  System.out.println("Yes");
		              //TODO: Answer was Yes
		          } /*else if (n == JOptionPane.CANCEL_OPTION) {
		              //TODO: Answer was Yes
		        	  System.out.println("Cancel");
		          } else {
		              //TODO: Clicked X
		        	  System.out.println("X");
		          }*/
		        fireEditingStopped();
		      }
		    });
	  }

	  public Component getTableCellEditorComponent(JTable table, Object value,
	                   boolean isSelected, int row, int column) {
	    if (isSelected) {
	      button.setForeground(table.getSelectionForeground());
	      button.setBackground(table.getSelectionBackground());
	    } else{
	      button.setForeground(table.getForeground());
	      button.setBackground(table.getBackground());
	    }
	    label = (value ==null) ? "" : value.toString();
	    button.setText( "X"/*label*/ );
	    isPushed = true;
	    return button;
	  }

	  public Object getCellEditorValue() {
	    if (isPushed)  {
//	      DeleteMemberPopup dmp = new DeleteMemberPopup(MainApplicationWindow.getMainWindow().getJFrame(),GlobalData.getInstance().getMitglieder().get(Integer.parseInt(label)));
//	      dmp.displayDeleteDialog();
	      // TODO: hier Nachfrage, dann löschen in DB, GlobalData und Tabelle
	    }
	    isPushed = false;
	    return new String( label ) ;
	  }
	  
	  
	  
	  public boolean stopCellEditing() {
	    isPushed = false;
	    return super.stopCellEditing();
	  }

	  protected void fireEditingStopped() {
	    super.fireEditingStopped();
	  }
	}