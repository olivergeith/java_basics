package swt.examples.plugin.draw2d.example.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

public class RecttangleDecoration extends PolygonDecoration {

	public RecttangleDecoration(final String name) {
		super();
		final PointList pl = new PointList();
		pl.addPoint(-2, -1);
		pl.addPoint(-2, 1);
		pl.addPoint(2, 1);
		pl.addPoint(2, -1);
		setTemplate(pl);
		setBackgroundColor(ColorConstants.white);
		setForegroundColor(ColorConstants.black);

		setFill(true);
		setOutline(true);
		final Label l = new Label(name);
		l.setFont(new Font(Display.getCurrent(), new FontData[] { new FontData("Arial", 8, SWT.NONE) }));
		setToolTip(l);
		add(l);
	}

	@Override
	public void setRotation(final double angle) {
		super.setRotation(0);

	}

	@Override
	public void setScale(final double x, final double y) {
		super.setScale(3, 6);
	}

}
