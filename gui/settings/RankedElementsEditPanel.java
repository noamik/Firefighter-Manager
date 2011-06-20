package gui.settings;



import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.GlobalData;
import datascheme.IdStringMatcher;
import datascheme.RankedElement;

public class RankedElementsEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel ldg = null;
	private JLabel lpos = null;
	private JTextField item = null;
	private JTextField position = null;
	private JButton add = null;
	private IdStringMatcher ism = null;
	private String name = null;
	private RankedElemsTable gt = null;
	
	
	/**
	 * This is the default constructor
	 */
	public RankedElementsEditPanel(IdStringMatcher tism, String Name, RankedElemsTable tgt) {
		super();
		ism = tism;
		name = Name;
		gt = tgt;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gBC_AddButton = new GridBagConstraints();
		gBC_AddButton.insets = new Insets(0, 0, 2, 0);
		gBC_AddButton.gridy = 2;
		gBC_AddButton.gridwidth = 2;
		gBC_AddButton.gridx = 0;
		gBC_AddButton.fill = GridBagConstraints.BOTH;
		GridBagConstraints gBC_PosFeld = new GridBagConstraints();
		gBC_PosFeld.fill = GridBagConstraints.BOTH;
		gBC_PosFeld.gridy = 1;
		gBC_PosFeld.gridx = 1;
		GridBagConstraints gBC_DGFeld = new GridBagConstraints();
		gBC_DGFeld.fill = GridBagConstraints.BOTH;
		gBC_DGFeld.gridy = 1;
		gBC_DGFeld.weightx = 1.0;
		gBC_DGFeld.gridx = 0;
		GridBagConstraints gBC_PosLabel = new GridBagConstraints();
		gBC_PosLabel.gridx = 1;
		gBC_PosLabel.anchor = GridBagConstraints.NORTHWEST;
		gBC_PosLabel.gridy = 0;
		GridBagConstraints gBC_DGLabel = new GridBagConstraints();
		gBC_DGLabel.gridx = 0;
		gBC_DGLabel.anchor = GridBagConstraints.NORTHWEST;
		gBC_DGLabel.gridy = 0;
		this.setSize(200, 100);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createCompoundBorder(
    			BorderFactory.createTitledBorder(name + " hinzufügen:"),
    			BorderFactory.createEmptyBorder(5,5,5,5)));
		//TODO: switch function above later to: GUIHelper.addBorder(this, "Mitglied hinzufügen:");
		this.setPreferredSize(new Dimension(200,100));
		this.setMinimumSize(new Dimension(100,100));
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE,100));
		ldg = new JLabel(name);
		lpos = new JLabel("Pos");
		
		
		item = new JTextField(name);
		item.setMinimumSize(new Dimension(75,25));
		addFocusListener(item);
		position = new JTextField("99");
		position.setMinimumSize(new Dimension(30,25));
		addFocusListener(position);
		add = new JButton();
		add.setText(name + " hinzufügen");
		this.add(ldg, gBC_DGLabel);
		this.add(lpos, gBC_PosLabel);
		add.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				RankedElement elem = new RankedElement(item.getText(),Integer.parseInt(position.getText()));
				if(name.equals("Ausbildung")) {
					GlobalData.getInstance().addTraining(GlobalData.getInstance().getLehrgänge().getMaxId()+1, elem);
				}
				if(name.equals("Dienstgrade")) {
					GlobalData.getInstance().addRank(GlobalData.getInstance().getDienstgrade().getMaxId()+1, elem);
				}
				if(name.equals("Status")) {
					GlobalData.getInstance().addStatus(GlobalData.getInstance().getMemberStatus().getMaxId()+1, elem);
				}
				gt.getGenericTable().setData(gt.createTableArray(ism));
				gt.getGenericTable().fireTableDataChanged();
			}
		});
		this.add(item, gBC_DGFeld);
		this.add(position, gBC_PosFeld);
		this.add(add, gBC_AddButton);
	}
	
	private void addFocusListener(JTextField jtf) {
		jtf.addFocusListener(new java.awt.event.FocusListener() {
			public void focusGained(java.awt.event.FocusEvent e) {
				((JTextField)e.getSource()).selectAll();
			}
			public void focusLost(java.awt.event.FocusEvent e) {
				((JTextField)e.getSource()).select(0, 0);
			}
		});
	}
	

}
