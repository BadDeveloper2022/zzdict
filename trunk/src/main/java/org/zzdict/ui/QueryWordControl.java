package org.zzdict.ui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class QueryWordControl  {
	/**
	 * parent control
	 */
	Composite parent;
	
	/**
	 * backButton, users click it to view previous queried word
	 */
	Button backButton;
	
	/**
	 * forwordButton, users click it to view next queried word
	 */
	Button forwardButton;
	
	/**
	 * queryButton, users click it to query word's explanation
	 */
	Button queryButton;
	
	/**
	 * input field to input word
	 */
	Text wordInputField;
	
	/**
	 * browser to display word's explanation
	 */
	Browser explanationBrowser;
	
	/**
	 * a toggle button to show autoScan status 
	 */
	Button autoScanStatusCheckBox;
	
	static ResourceBundle resourceBundle;
	
	/**
	 * Default constructor 
	 */
	public QueryWordControl(Composite parent) {
		super();
		this.parent = parent;
	}

	/**
	 * Gets a string from the resource bundle.
	 * We don't want to crash because of a missing String.
	 * Returns the key if not found.
	 */
	static String getResourceString(String key) {
		try {
			return resourceBundle.getString(key);
		} catch (MissingResourceException e) {
			return key;
		} catch (NullPointerException e) {
			return "!" + key + "!";
		}			
	}
		
	/**
	 * main method to test swt code
	 */
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Hello World");
		shell.setSize(200, 100);
		shell.open();
		while(!shell.isDisposed()){
			if(!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
