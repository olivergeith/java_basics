package swt.examples.plugin.draw2d.example.figure;

import java.io.File;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import swt.examples.plugin.draw2d.example.data.ModTabElement;

public class PortFigure extends AmberFigure implements RotatableDecoration {

	private final ModTabElement modtabElement;
	private final String name;

	public PortFigure(final ModTabElement modtabElement, final String name) {
		this.modtabElement = modtabElement;
		this.name = name;
		setToolTip(new Label(name));
		setBackgroundColor(ColorConstants.white);
		setLayoutManager(new ToolbarLayout());
		setPreferredSize(25, 25);
		add(new Label(name));
	}

	public String getName() {
		return name;
	}

	public Object getModtabElement() {
		return modtabElement;
	}

	/** builds a label with a list of strings */
	private Label getListLabel(final List<String> list) {
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

	/** build an image starting from relative path wrt user directory */
	private static Image getImage(final String path) {
		final File f = new File(System.getProperty("user.dir"), path);
		return new Image(Display.getCurrent(), f.getAbsolutePath());
	}

	@Override
	public void setReferencePoint(final Point p) {
		setLocation(p);

	}

}
