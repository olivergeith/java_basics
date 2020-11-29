package swt.examples.plugin.draw2d.example;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/** class for testing SWT with Draw2d */
public class EvpSpyPart {

	/** Application entry point */
	public static void main(final String[] args) {
		new EvpSpyPart().open();
	}

	private Figure root;
	private EvpController evpController;

	/** Open a Shell call a method for drawing */
	public void open() {
		final Shell shell = new Shell(new Display());
		shell.setSize(800, 250);
		shell.setText("My Main View");
		shell.setLayout(new GridLayout());

		postConstruct(shell);

		final Display display = shell.getDisplay();
		shell.open();
		while (!shell.isDisposed()) {
			while (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private void postConstruct(final Composite parent) {
		final Canvas canvas = new Canvas(parent, SWT.DOUBLE_BUFFERED | SWT.V_SCROLL | SWT.H_SCROLL);
		canvas.setLayoutData(new GridData(GridData.FILL_BOTH));
		canvas.setBackground(ColorConstants.lightGray);
		final LightweightSystem lws = new LightweightSystem(canvas);

		root = new Figure();
		root.setFont(parent.getFont());
		root.setLayoutManager(new XYLayout());
		lws.setContents(root);
		evpController = new EvpController(root);
		evpController.draw();
	}

}
