package og.basics.gui.html.DJNativeSwing;

/*
 * Christopher Deckers (chrriis@nextencia.net)
 * http://www.nextencia.net
 *
 * See the file "readme.txt" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

/**
 * @author Christopher Deckers
 */
public class SimpleWebBrowserExample extends JPanel {

	public SimpleWebBrowserExample() {
		super(new BorderLayout());
		final JPanel webBrowserPanel = new JPanel(new BorderLayout());
		webBrowserPanel.setBorder(BorderFactory.createTitledBorder("Native Web Browser component"));
		final JWebBrowser webBrowser = new JWebBrowser();
		webBrowser.navigate("http://www.google.com");
		// webBrowser.navigate("file:///./help/About.htm");
		webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
		add(webBrowserPanel, BorderLayout.CENTER);
		// Create an additional bar allowing to show/hide the menu bar of the
		// web browser.
		final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));
		final JCheckBox menuBarCheckBox = new JCheckBox("Menu Bar", webBrowser.isMenuBarVisible());
		menuBarCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(final ItemEvent e) {
				webBrowser.setMenuBarVisible(e.getStateChange() == ItemEvent.SELECTED);
			}
		});
		buttonPanel.add(menuBarCheckBox);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	/* Standard main method to try that test as a standalone application. */
	public static void main(final String[] args) {
		UIUtils.setPreferredLookAndFeel();
		NativeInterface.open();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				final JFrame frame = new JFrame("DJ Native Swing Test");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(new SimpleWebBrowserExample(), BorderLayout.CENTER);
				frame.setSize(800, 600);
				frame.setLocationByPlatform(true);
				frame.setVisible(true);
			}
		});
		NativeInterface.runEventPump();
	}

}
