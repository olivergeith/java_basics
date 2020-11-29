package swt.examples.plugin.draw2d.example.figure;

import java.io.File;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import swt.examples.plugin.draw2d.example.data.ModTabElement;

public class EvpFigure extends AmberFigure {

	private final ModTabElement modtabElement;
	private final String name;
	private final String type;

	public EvpFigure(final ModTabElement modtabElement) {
		this.modtabElement = modtabElement;
		name = modtabElement.getName();
		type = modtabElement.getType();

		setBackgroundColor(ColorConstants.white);
		setLayoutManager(new ToolbarLayout());
		setPreferredSize(80, 50);
		setFont(new Font(Display.getCurrent(), new FontData[] { new FontData("Arial", 8, SWT.NONE) }));

		add(new Label(name, getImage("resources/img/class_obj.gif")));
		add(new Label(type));

	}

	public String getName() {
		return name;
	}

	public ModTabElement getModtabElement() {
		return modtabElement;
	}

	/** builds a label with a list of strings */
	@SuppressWarnings("unused")
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

}
