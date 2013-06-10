package og.basics.util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

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
}
