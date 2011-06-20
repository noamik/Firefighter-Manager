package gui;

import gui.generic.EditEntryPanel;

import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.KeyStroke;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;


public class MainApplicationWindow {

	private static MainApplicationWindow instance = new MainApplicationWindow();  //  @jve:decl-index=0:
	
	private JFrame jFrame = null;
	private JMenuBar jJMenuBar = null;
	private JMenu fileMenu = null;
//	private JMenu editMenu = null;
	private JMenu helpMenu = null;
	private JMenuItem exitMenuItem = null;
	private JMenuItem aboutMenuItem = null;
//	private JMenuItem cutMenuItem = null;
//	private JMenuItem copyMenuItem = null;
//	private JMenuItem pasteMenuItem = null;
	private JMenuItem saveMenuItem = null;
	private JDialog aboutDialog = null;
	private JPanel aboutContentPane = null;
	private JLabel aboutVersionLabel = null;
	
	
	private JTabbedPane tabbedMainPane = null;
    private JPanel jContentPane = null;
    private JTable memberTable = null;
	
	private JSplitPane settingsOuterSplitPane = null;
	private JSplitPane settingsTopSplitPane = null;
	private JSplitPane settingsBotSplitPane = null;
	
	private JPanel genericSettingsBotRightPane = null;
	
	private MemberTable mt = null;  //  @jve:decl-index=0:
	private EditEntryPanel memberTablePanel = null;  //  @jve:decl-index=0:
	private RankedElementsSettingsPanel tsp = null;
	private RankedElementsSettingsPanel rsp = null;
	private RankedElementsSettingsPanel ssp = null;

	private MainApplicationWindow() {
	}
	
	public static MainApplicationWindow getMainWindow() {
		return instance;
	}
	
	/**
	 * This method initializes jFrame
	 * 
	 * @return javax.swing.JFrame
	 */
	public JFrame getJFrame() {
		if (jFrame == null) {
			if(util.util.DebugMainWindow)
				System.out.println("Creating Main Frame");
			jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setJMenuBar(getJJMenuBar());
			jFrame.setSize(800, 600);
			jFrame.setContentPane(getJContentPane());
			jFrame.setTitle("FF-Verwaltung");
			jFrame.setMinimumSize(new Dimension(400,300));
		}
		return jFrame;
	}
	

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			if(util.util.DebugMainWindow)
				System.out.println("Creating Content Frame");
			jContentPane = new JPanel();
        	jContentPane.setLayout(new GridLayout(0,1));
        	jContentPane.add(getTabbedPane());
		}
        return jContentPane;
	}
	
	private JTabbedPane getTabbedPane() {
		if(tabbedMainPane == null) {
			if(util.util.DebugMainWindow)
				System.out.println("Creating Tabbed Pane");
			tabbedMainPane = new JTabbedPane();
        	tabbedMainPane.addTab("Mitgliederverwaltung", getMemberPane());
        	tabbedMainPane.addTab("Mitgliederausbildungen", new JPanel());
        	tabbedMainPane.addTab("Einstellungen", getTestPane());
		}
        return tabbedMainPane;
	}
	
	private JPanel getTestPane() {
		final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1,0));
        panel3.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Einstellungen"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        panel3.add(getSettings());
        return panel3;
	}
	
	private JSplitPane getSettings() {
		//Put the editor pane and the text pane in a split pane.
		settingsTopSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                getTrainTable(),
                getRankTable());
		settingsBotSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                getStatusTable(),
                getGeneralSettings());
        settingsOuterSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
        										settingsTopSplitPane,
        										settingsBotSplitPane);
        settingsOuterSplitPane.setOneTouchExpandable(true);
        settingsOuterSplitPane.setResizeWeight(0.5);
        settingsTopSplitPane.setOneTouchExpandable(true);
        settingsTopSplitPane.setResizeWeight(0.5);
        settingsBotSplitPane.setOneTouchExpandable(true);
        settingsBotSplitPane.setResizeWeight(0.5);
        return settingsOuterSplitPane;
	}
	
	public RankedElementsSettingsPanel getTrainTable() {
		if(tsp == null)
			tsp = new RankedElementsSettingsPanel("Ausbildung");
        return tsp;
	}
	public RankedElementsSettingsPanel getRankTable() {
		if(rsp == null)
			rsp = new RankedElementsSettingsPanel("Dienstgrade");
        return rsp;
	}
	
	public RankedElementsSettingsPanel getStatusTable() {
		if(ssp == null)
			ssp = new RankedElementsSettingsPanel("Status");
        return ssp;
	}
	private JPanel getGeneralSettings() {
		genericSettingsBotRightPane = new JPanel();
		genericSettingsBotRightPane.setLayout(new GridLayout(0,1));
		genericSettingsBotRightPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Allgemeine Einstellungen"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        return genericSettingsBotRightPane;
	}
	
	/**
	 * @return
	 */
	private JPanel getMemberPane() {
		final JPanel panel2 = new JPanel();
		if(util.util.DebugMainWindow)
			System.out.println("Creating Member Pane");
        panel2.setLayout(new GridLayout(0,1));
        memberTablePanel = initMemberJPanel();
        panel2.add(memberTablePanel.getPanel());
        return panel2;	
	}
	
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private EditEntryPanel initMemberJPanel() {
		if(util.util.DebugMainWindow)
			System.out.println("Initializing Member Pane");
		return new EditEntryPanel(null,null/*"Mitgliedertabelle"*/,getJMemberTable(),new MemberEditPanel());
	}
	
	private JTable getJMemberTable() {
		if(util.util.DebugMainWindow)
			System.out.println("Creating Tabbed Pane");
		memberTable = initMemberTable().getTable();
		return memberTable;
	}
	
	public MemberTable getMemberTable() {
		return mt;
	}
	
	
	
	private MemberTable initMemberTable() {
		if(util.util.DebugMemberPane)
			System.out.println("Creating Member Table");
		mt = new MemberTable();
		return mt;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFileMenu());
//			jJMenuBar.add(getEditMenu());
			jJMenuBar.add(getHelpMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("File");
			fileMenu.add(getSaveMenuItem());
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
	}

//	/**
//	 * This method initializes jMenu	
//	 * 	
//	 * @return javax.swing.JMenu	
//	 */
//	private JMenu getEditMenu() {
//		if (editMenu == null) {
//			editMenu = new JMenu();
//			editMenu.setText("Edit");
//			editMenu.add(getCutMenuItem());
//			editMenu.add(getCopyMenuItem());
//			editMenu.add(getPasteMenuItem());
//		}
//		return editMenu;
//	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			helpMenu.add(getAboutMenuItem());
		}
		return helpMenu;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new JMenuItem();
			aboutMenuItem.setText("About");
			aboutMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog aboutDialog = getAboutDialog();
					aboutDialog.pack();
					Point loc = getJFrame().getLocation();
					loc.translate(20, 20);
					aboutDialog.setLocation(loc);
					aboutDialog.setVisible(true);
				}
			});
		}
		return aboutMenuItem;
	}

	/**
	 * This method initializes aboutDialog	
	 * 	
	 * @return javax.swing.JDialog
	 */
	private JDialog getAboutDialog() {
		if (aboutDialog == null) {
			aboutDialog = new JDialog(getJFrame(), true);
			aboutDialog.setTitle("About");
			aboutDialog.setContentPane(getAboutContentPane());
		}
		return aboutDialog;
	}

	/**
	 * This method initializes aboutContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getAboutContentPane() {
		if (aboutContentPane == null) {
			aboutContentPane = new JPanel();
			aboutContentPane.setLayout(new BorderLayout());
			aboutContentPane.add(getAboutVersionLabel(), BorderLayout.CENTER);
		}
		return aboutContentPane;
	}

	/**
	 * This method initializes aboutVersionLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getAboutVersionLabel() {
		if (aboutVersionLabel == null) {
			aboutVersionLabel = new JLabel();
			aboutVersionLabel.setText("FF-Verwaltung Version 0.0.1");
			aboutVersionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return aboutVersionLabel;
	}

//	/**
//	 * This method initializes jMenuItem	
//	 * 	
//	 * @return javax.swing.JMenuItem	
//	 */
//	private JMenuItem getCutMenuItem() {
//		if (cutMenuItem == null) {
//			cutMenuItem = new JMenuItem();
//			cutMenuItem.setText("Cut");
//			cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
//					Event.CTRL_MASK, true));
//		}
//		return cutMenuItem;
//	}

//	/**
//	 * This method initializes jMenuItem	
//	 * 	
//	 * @return javax.swing.JMenuItem	
//	 */
//	private JMenuItem getCopyMenuItem() {
//		if (copyMenuItem == null) {
//			copyMenuItem = new JMenuItem();
//			copyMenuItem.setText("Copy");
//			copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
//					Event.CTRL_MASK, true));
//			copyMenuItem.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					//TODO: Copy
////					JFrame frame = new JFrame("Use Clipboard");
////					final Clipboard clipboard = frame.getToolkit().getSystemClipboard();
////					Transferable clipData = clipboard.getContents(clipboard);
////					try {
////					   if(clipData.isDataFlavorSupported(DataFlavor.stringFlavor)) {
////						   String s = (String)(clipData.getTransferData(DataFlavor.stringFlavor));
////						   area.replaceSelection(s);
////					   }
////					} catch (Exception ufe) {}
//				}
//			});
//		}
//		return copyMenuItem;
//	}

//	/**
//	 * This method initializes jMenuItem	
//	 * 	
//	 * @return javax.swing.JMenuItem	
//	 */
//	private JMenuItem getPasteMenuItem() {
//		if (pasteMenuItem == null) {
//			pasteMenuItem = new JMenuItem();
//			pasteMenuItem.setText("Paste");
//			pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
//					Event.CTRL_MASK, true));
//		}
//		return pasteMenuItem;
//	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSaveMenuItem() {
		if (saveMenuItem == null) {
			saveMenuItem = new JMenuItem();
			saveMenuItem.setText("Save");
			saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					Event.CTRL_MASK, true));
		}
		return saveMenuItem;
	}
	
	
	
	public static void launchWindow() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainApplicationWindow application = MainApplicationWindow.getMainWindow();
				application.getJFrame().setVisible(true);
			}
		});
	}

}
