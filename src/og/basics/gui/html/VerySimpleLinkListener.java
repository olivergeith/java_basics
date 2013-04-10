package og.basics.gui.html;

//SimpleLinkListener.java
//A hyperlink listener for use with JEditorPane.  This
//listener changes the cursor over hyperlinks based on enter/exit
//events and also loads a new page when a valid hyperlink is clicked.
//

import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class VerySimpleLinkListener implements HyperlinkListener {

	// private final JEditorPane pane; // The pane we're using to display HTML
	//
	// private final JTextField urlField; // An optional text field for showing
	// the current URL being displayed

	private final JLabel statusBar; // An optional label for showing where

	// a link would take you

	public VerySimpleLinkListener(final JEditorPane jep, final JTextField jtf, final JLabel jl) {
		// pane = jep;
		// urlField = jtf;
		statusBar = jl;
	}

	public VerySimpleLinkListener(final JEditorPane jep) {
		this(jep, null, null);
	}

	public void hyperlinkUpdate(final HyperlinkEvent he) {
		final HyperlinkEvent.EventType type = he.getEventType();
		if (type == HyperlinkEvent.EventType.ENTERED) {
			// Enter event. Fill in the status bar.
			if (statusBar != null) {
				statusBar.setText(he.getURL().toString());
			}
		} else if (type == HyperlinkEvent.EventType.EXITED) {
			// Exit event. Clear the status bar.
			if (statusBar != null) {
				statusBar.setText(" "); // Must be a space or it disappears
			}
		} else if (type == HyperlinkEvent.EventType.ACTIVATED) {
			try {
				openUrlInExternalBrowser(he.getURL().toString());
			} catch (final IOException e1) {
				e1.printStackTrace();
			} catch (final URISyntaxException e1) {
				e1.printStackTrace();
			}
			// // Jump event. Get the URL, and, if it's not null, switch to that
			// // page in the main editor pane and update the "site url" label.
			// if (he instanceof HTMLFrameHyperlinkEvent) {
			// // Ahh, frame event; handlegetU this separately.
			// final HTMLFrameHyperlinkEvent evt = (HTMLFrameHyperlinkEvent) he;
			// final HTMLDocument doc = (HTMLDocument) pane.getDocument();
			// doc.processHTMLFrameHyperlinkEvent(evt);
			// } else {
			// try {
			// System.out.println(he.getURL());
			// pane.setPage(he.getURL());
			// if (urlField != null) {
			// urlField.setText(he.getURL().toString());
			// }
			// } catch (final FileNotFoundException fnfe) {
			// pane.setText("Could not open file: <tt>" + he.getURL() +
			// "</tt>.<hr>");
			// } catch (final Exception e) {
			// e.printStackTrace();
			// }
			// }
		}
	}

	public void openUrlInExternalBrowser(final String url) throws IOException, URISyntaxException {
		if (java.awt.Desktop.isDesktopSupported()) {
			final java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

			if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
				final java.net.URI uri = new java.net.URI(url);
				desktop.browse(uri);
			}
		}
	}
}
