package og.basics.util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class StaticExecutor {

	/**
	 * Opens a folder in the System FileManager (Windows in Explorer)
	 * 
	 * @param path
	 */
	public static void openFolder(final String path) {
		final File f = new File(path);
		System.out.println("Path: " + f.getAbsolutePath());
		try {
			System.out.println("Path: " + f.getCanonicalPath());
			Desktop.getDesktop().browse(new File(f.getCanonicalPath()).toURI());
		} catch (final IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void openUrlInExternalBrowser(final String url) {
		if (java.awt.Desktop.isDesktopSupported()) {
			final java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

			if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
				java.net.URI uri;
				try {
					uri = new java.net.URI(url);
					desktop.browse(uri);
				} catch (final URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (final IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
