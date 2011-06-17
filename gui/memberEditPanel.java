package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import data.GlobalData;
import datascheme.IdStringMatcher;
import datascheme.Mitglied;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class memberEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lname = null;
	private JLabel lvorname = null;
	private JLabel ldg = null;
	private JLabel lgeb = null;
	private JLabel laufn = null;
	private JLabel lstatus = null;
	private JTextField name = null;
	private JTextField vorName = null;
	private JComboBox dienstGrad = null;
	private JTextField geb = null;
	private JTextField aufn = null;
	private JComboBox status = null;
	private JButton add = null;
	
	
	/**
	 * This is the default constructor
	 */
	public memberEditPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraintsAddButton = new GridBagConstraints();
		gridBagConstraintsAddButton.insets = new Insets(0, 0, 2, 0);
		gridBagConstraintsAddButton.gridy = 2;
		gridBagConstraintsAddButton.ipadx = -45;
		gridBagConstraintsAddButton.ipady = -4;
		gridBagConstraintsAddButton.gridwidth = 6;
		gridBagConstraintsAddButton.gridx = 0;
		gridBagConstraintsAddButton.fill = GridBagConstraints.HORIZONTAL;
		GridBagConstraints gridBagConstraintsStatusBox = new GridBagConstraints();
		gridBagConstraintsStatusBox.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraintsStatusBox.gridy = 1;
		gridBagConstraintsStatusBox.ipadx = 98;
		gridBagConstraintsStatusBox.ipady = -3;
		gridBagConstraintsStatusBox.weightx = 1.0;
		gridBagConstraintsStatusBox.gridx = 5;
		GridBagConstraints gridBagConstraintsAufnComboBox = new GridBagConstraints();
		gridBagConstraintsAufnComboBox.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraintsAufnComboBox.gridy = 1;
		gridBagConstraintsAufnComboBox.ipadx = 23;
		gridBagConstraintsAufnComboBox.ipady = 2;
		gridBagConstraintsAufnComboBox.weightx = 1.0;
		gridBagConstraintsAufnComboBox.gridx = 4;
		GridBagConstraints gridBagConstraintsGebFeld = new GridBagConstraints();
		gridBagConstraintsGebFeld.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraintsGebFeld.gridy = 1;
		gridBagConstraintsGebFeld.ipadx = 36;
		gridBagConstraintsGebFeld.ipady = 2;
		gridBagConstraintsGebFeld.weightx = 1.0;
		gridBagConstraintsGebFeld.gridx = 3;
		GridBagConstraints gridBagConstraintsDGFeld = new GridBagConstraints();
		gridBagConstraintsDGFeld.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraintsDGFeld.gridy = 1;
		gridBagConstraintsDGFeld.ipadx = 98;
		gridBagConstraintsDGFeld.ipady = -3;
		gridBagConstraintsDGFeld.weightx = 1.0;
		gridBagConstraintsDGFeld.gridx = 2;
		GridBagConstraints gridBagConstraintsVorNFeld = new GridBagConstraints();
		gridBagConstraintsVorNFeld.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraintsVorNFeld.gridy = 1;
		gridBagConstraintsVorNFeld.ipadx = 70;
		gridBagConstraintsVorNFeld.ipady = 2;
		gridBagConstraintsVorNFeld.weightx = 1.0;
		gridBagConstraintsVorNFeld.gridx = 1;
		GridBagConstraints gridBagConstraintsNameFeld = new GridBagConstraints();
		gridBagConstraintsNameFeld.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraintsNameFeld.gridy = 1;
		gridBagConstraintsNameFeld.ipadx = 90;
		gridBagConstraintsNameFeld.ipady = 2;
		gridBagConstraintsNameFeld.weightx = 1.0;
		gridBagConstraintsNameFeld.gridx = 0;
		GridBagConstraints gridBagConstraintsStatusLabel = new GridBagConstraints();
		gridBagConstraintsStatusLabel.gridx = 5;
		gridBagConstraintsStatusLabel.ipadx = 83;
		gridBagConstraintsStatusLabel.ipady = 6;
		gridBagConstraintsStatusLabel.gridy = 0;
		GridBagConstraints gridBagConstraintsAufnLabel = new GridBagConstraints();
		gridBagConstraintsAufnLabel.gridx = 4;
		gridBagConstraintsAufnLabel.ipadx = 14;
		gridBagConstraintsAufnLabel.ipady = 6;
		gridBagConstraintsAufnLabel.gridy = 0;
		GridBagConstraints gridBagConstraintsGebLabel = new GridBagConstraints();
		gridBagConstraintsGebLabel.gridx = 3;
		gridBagConstraintsGebLabel.ipadx = 28;
		gridBagConstraintsGebLabel.ipady = 6;
		gridBagConstraintsGebLabel.gridy = 0;
		GridBagConstraints gridBagConstraintsDGLabel = new GridBagConstraints();
		gridBagConstraintsDGLabel.gridx = 2;
		gridBagConstraintsDGLabel.ipadx = 51;
		gridBagConstraintsDGLabel.ipady = 6;
		gridBagConstraintsDGLabel.gridy = 0;
		GridBagConstraints gridBagConstraintsVorNLabel = new GridBagConstraints();
		gridBagConstraintsVorNLabel.gridx = 1;
		gridBagConstraintsVorNLabel.ipadx = 67;
		gridBagConstraintsVorNLabel.ipady = 6;
		gridBagConstraintsVorNLabel.gridy = 0;
		GridBagConstraints gridBagConstraintsNameLabel = new GridBagConstraints();
		gridBagConstraintsNameLabel.gridx = 0;
		gridBagConstraintsNameLabel.ipadx = 90;
		gridBagConstraintsNameLabel.ipady = 6;
		gridBagConstraintsNameLabel.gridy = 0;
		this.setSize(600, 100);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createCompoundBorder(
    			BorderFactory.createTitledBorder("Mitglied hinzufügen:"),
    			BorderFactory.createEmptyBorder(5,5,5,5)));
		//TODO: switch function above later to: GUIHelper.addBorder(this, "Mitglied hinzufügen:");
		this.setPreferredSize(new Dimension(450,100));
		this.setMinimumSize(new Dimension(100,100));
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE,100));
		lname = new JLabel("Name");
		lvorname = new JLabel("Vorname");
		lgeb = new JLabel("Geburtsdatum");
		laufn = new JLabel("Aufnahmedatum");
		ldg = new JLabel("Dienstgrad");
		lstatus = new JLabel("Status");
		
		name = new JTextField("Name");
		name.setMinimumSize(new Dimension(50,20));
		addFocusListener(name);
		vorName = new JTextField("Vorname");
		vorName.setMinimumSize(new Dimension(50,20));
		addFocusListener(vorName);
		dienstGrad = new JComboBox();
		dienstGrad.setMinimumSize(new Dimension(50,20));
		geb = new JTextField("Geburtsdatum");
		geb.setMinimumSize(new Dimension(50,20));
		addFocusListener(geb);
		aufn = new JTextField("Aufnahmedatum");
		aufn.setMinimumSize(new Dimension(50,20));
		addFocusListener(aufn);
		status = new JComboBox();
		status.setMinimumSize(new Dimension(50,20));
		
		add = new JButton();
		add.setText("Mitglied hinzufügen");
		add.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
//				System.out.println("actionPerformed()");
				Integer newId = GlobalData.getInstance().getMaxMemberId() + 1;
				Integer newdg = GlobalData.getInstance().getDienstgrade().getId((String)dienstGrad.getSelectedItem());
				Integer newst = GlobalData.getInstance().getMemberStatus().getId((String)status.getSelectedItem());
				Mitglied newMember = new Mitglied(newId,name.getText(),vorName.getText(),newdg,util.util.StringToDate(aufn.getText()),util.util.StringToDate(geb.getText()),null,null,newst);
//				System.out.println("New Member: " + newMember.toString());
				GlobalData.getInstance().addMember(newMember);
			}
		});
		getDienstgrade();
		getStatus();
		this.add(lname, gridBagConstraintsNameLabel);
		this.add(lvorname, gridBagConstraintsVorNLabel);
		this.add(ldg, gridBagConstraintsDGLabel);
		this.add(lgeb, gridBagConstraintsGebLabel);
		this.add(laufn, gridBagConstraintsAufnLabel);
		this.add(lstatus, gridBagConstraintsStatusLabel);
		this.add(name, gridBagConstraintsNameFeld);
		this.add(vorName, gridBagConstraintsVorNFeld);
		this.add(dienstGrad, gridBagConstraintsDGFeld);
		this.add(geb, gridBagConstraintsGebFeld);
		this.add(aufn, gridBagConstraintsAufnComboBox);
		this.add(status, gridBagConstraintsStatusBox);
		this.add(add, gridBagConstraintsAddButton);
	}
	
	private void addFocusListener(JTextField jtf) {
		jtf.addFocusListener(new java.awt.event.FocusListener() {
			public void focusGained(java.awt.event.FocusEvent e) {
//				System.out.println("focusGained()"); // TODO Auto-generated Event stub focusGained()
				((JTextField)e.getSource()).selectAll();
			}
			public void focusLost(java.awt.event.FocusEvent e) {
//				System.out.println("focusLost()");
				((JTextField)e.getSource()).select(0, 0);
			}
		});
//		jtf.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent e) {
//				System.out.println("actionPerformed() - Wird bei Enter generiert"); // TODO Auto-generated Event stub actionPerformed()
//			}
//		});
//		jtf.addCaretListener(new javax.swing.event.CaretListener() {
//			public void caretUpdate(javax.swing.event.CaretEvent e) {
//				System.out.println("caretUpdate() - Wird bei jeder Cursorbewegung generiert"); // TODO Auto-generated Event stub caretUpdate()
//			}
//		});
	}
	
	private void getDienstgrade() {
		IdStringMatcher Dienstgrade = data.GlobalData.getInstance().getDienstgrade();
		Iterator<String> it = Dienstgrade.getElements().values().iterator();
		while(it.hasNext()) {
			dienstGrad.addItem(it.next());
		}
	}
	
	private void getStatus() {
		IdStringMatcher Status = data.GlobalData.getInstance().getMemberStatus();
		Iterator<String> it = Status.getElements().values().iterator();
		while(it.hasNext()) {
			status.addItem(it.next());
		}
	}
	

}
