/**
 * Main Application Class
 *
 * Initializes and manages the application
 */

import gui.MainApplicationWindow;

import javax.swing.*;

/**
 * @author noamik
 *
 */
public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		initApplicationLaF();
		new util.util();
		data.GlobalData.getInstance();
		System.out.println("Global instance created");
		MainApplicationWindow.launchWindow();	
		System.out.println("Created new GUI");
	}
	
	/**
	 * Initializes Look and Feel of this application
	 */
	private static void initApplicationLaF() {
//		// TODO Auto-generated method stub
		String nameOS = "os.name";  
//		String versionOS = "os.version";  
//		String architectureOS = "os.arch";
		String OSname = System.getProperty(nameOS);
//		String OSversion = System.getProperty(versionOS);
//		String OSarch = System.getProperty(architectureOS);
		try {
			String LF;
			if(OSname.toLowerCase().contains("linux"))
				LF = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
			else if(OSname.toLowerCase().contains("windows"))
				LF = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			else
				LF = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(LF);
			System.out.println("OSname="+OSname + " - Setting L&F: " + LF + " - System-L&F: "+UIManager.getSystemLookAndFeelClassName());
	    }
	    catch (UnsupportedLookAndFeelException e) {System.out.println("L&F not supported");}
	    catch (ClassNotFoundException e) {System.out.println("L&F not found");}
	    catch (InstantiationException e) {}
	    catch (IllegalAccessException e) {}
	}

}
