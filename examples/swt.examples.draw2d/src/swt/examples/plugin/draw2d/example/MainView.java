package swt.examples.plugin.draw2d.example;

import java.io.File;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/** class for testing SWT with Draw2d */
public class MainView {

	/** Application entry point */
	public static void main(final String[] args) {
		new MainView().open();
	}

	/** Open a Shell call a method for drawing */
	public void open() {
		final Shell shell = new Shell(new Display());
		shell.setSize(400, 250);
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
		// build diagram
		final Canvas canvas = buildDiagram(parent);
		canvas.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	/** Instantiate the root figure, where we draw figures */
	private Canvas buildDiagram(final Composite parent) {

		// instantiate root figure
		final Figure root = new Figure();
		root.setFont(parent.getFont());
		root.setLayoutManager(new XYLayout());

		// insantiate a canvas on which to draw
		final Canvas canvas = new Canvas(parent, SWT.DOUBLE_BUFFERED);
		canvas.setBackground(ColorConstants.lightGray);
		final LightweightSystem lws = new LightweightSystem(canvas);
		lws.setContents(root);

		// this code for drawing
		draw(root);
		//
		return canvas;
	}

	/** This method draws figures on the root */
	private void draw(final Figure root) {
		final Figure first = myClassFigure("First", new String[] { "- name:String" },
				new String[] { "+ First(String)", "+ getName():String", "+ setName(String)" });
		root.add(first, new Rectangle(new Point(10, 10), first.getPreferredSize()));

		final Figure second = myClassFigure("Second", new String[] { "- email: String" },
				new String[] { "+ getEmail():String", "+ setEmail(String)" });
		root.add(second, new Rectangle(new Point(200, 100), second.getPreferredSize()));

		root.add(myConnection(first, second));
	}

	/** specific method to draw a block figure */
	public Figure myBlockFigure(final String name) {
		final RectangleFigure f = new RectangleFigure();
		f.setBackgroundColor(ColorConstants.lightGreen);
		f.setLayoutManager(new ToolbarLayout());
		f.setPreferredSize(100, 100);
		f.add(new Label(name));
		new MouseDragListenerRaster(null);
		return f;
	}

	/** return a connection figure with chopbox between two figures */
	public Connection myConnection(final IFigure fig1, final IFigure fig2) {
		final PolylineConnection conn = new PolylineConnection();
		conn.setSourceAnchor(new ChopboxAnchor(fig1));
		conn.setTargetAnchor(new ChopboxAnchor(fig2));
		conn.setSourceDecoration(myPolygonDecoration());
		addMultiplicity(conn);
		return conn;
	}

	/** cerate a poligon decoration that looks like a composition */
	public PolygonDecoration myPolygonDecoration() {
		final PolygonDecoration deco = new PolygonDecoration();
		final PointList pl = new PointList();
		pl.addPoint(0, 0);
		pl.addPoint(-2, 2);
		pl.addPoint(-4, 0);
		pl.addPoint(-2, -2);
		deco.setTemplate(pl);
		return deco;
	}

	/** adds a multiplicity label linked to targte endpoint */
	public void addMultiplicity(final PolylineConnection c) {
		final ConnectionEndpointLocator targetEL = new ConnectionEndpointLocator(c, true);
		targetEL.setVDistance(10);
		final Label multiplicity = new Label("1..*");
		c.add(multiplicity, targetEL);
	}

	/** this method build a Class image figure */
	public Figure myClassFigure(final String name, final String[] fields, final String[] methods) {
		final RectangleFigure f = new RectangleFigure();
		f.setBackgroundColor(ColorConstants.lightGray);
		f.setLayoutManager(new ToolbarLayout());
		f.setPreferredSize(150, 110);
		//
		f.add(new Label(name, getImage("resources/img/class_obj.gif")));
		f.add(myListLabel(fields));
		f.add(myListLabel(methods));
		//
		new MouseDragListenerRaster(null);
		return f;
	}

	/** build an image starting from relative path wrt user directory */
	private static Image getImage(final String path) {
		final File f = new File(System.getProperty("user.dir"), path);
		return new Image(Display.getCurrent(), f.getAbsolutePath());
	}

	/** builds a label with a list of strings */
	public Label myListLabel(final String[] list) {
		String text = "";
		for (final String s : list) {
			text += s + "\n";
		}
		final Label label = new Label(text) {
			@Override
			protected void paintBorder(final Graphics g) {
				final Rectangle r = getBounds();
				/** paints horizontal line on top of label */
				g.drawLine(r.x, r.y, r.x + r.width, r.y);
			};

			@Override
			public Insets getInsets() {
				// top, left, bottom, right
				return new Insets(5, 0, 0, 0);
			}
		};
		return label;
	}

}
