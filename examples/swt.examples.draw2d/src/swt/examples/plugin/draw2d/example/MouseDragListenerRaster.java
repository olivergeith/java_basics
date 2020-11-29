package swt.examples.plugin.draw2d.example;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import swt.examples.plugin.draw2d.example.figure.EvpFigure;

/** This Listener implements drag and drop for Draw2D Figure */
public class MouseDragListenerRaster implements MouseListener, MouseMotionListener {

	private Point location;
	private final EvpController evpController;

	public MouseDragListenerRaster(final EvpController evpController) {
		this.evpController = evpController;
	}

	@Override
	public void mousePressed(final MouseEvent me) {
		location = me.getLocation();
//		System.out.println(location);
		if (location.x < 0) {
			location.x = 0;
		}
		if (location.y < 0) {
			location.y = 0;
		}
		me.consume();
	}

	@Override
	public void mouseDragged(final MouseEvent me) {
		final Object source = me.getSource();
		if (source instanceof EvpFigure) {
			final EvpFigure figure = (EvpFigure) source;
			final Point newLocation = me.getLocation();
			if (newLocation.x < 0) {
				newLocation.x = 0;
			}
			if (newLocation.y < 0) {
				newLocation.y = 0;
			}

			if (location == null || newLocation == null) {
				return;
			}
			// Raster calculation
			int newX = (newLocation.x / 100) * 100 + 10;
			int newY = (newLocation.y / 70) * 70 + 10;

			final long others = evpController.otherFiguresAtPosition(new Point(newX, newY), figure);

			// bleiben wo man war, wenn da schon jemand ist .-..
			if (others != 0) {
				newX = location.x;
				newY = location.y;
			}

			final Point newRasterLocation = new Point(newX, newY);
//			System.out.println(newRasterLocation);

			final Dimension offset = newRasterLocation.getDifference(location);
			if (offset.width == 0 && offset.height == 0) {
				return;
			}

			location = newRasterLocation;
			// old Bounds are dirty
			figure.getUpdateManager().addDirtyRegion(figure.getParent(), figure.getBounds());
			// translate figure
			figure.setLocation(location);
			// new Bounds are dirty
			figure.getUpdateManager().addDirtyRegion(figure.getParent(), figure.getBounds());
			// new Bounds: set parent constraint
			figure.getParent().getLayoutManager().setConstraint(figure, figure.getBounds());
			//

			figure.setBackgroundColor(ColorConstants.yellow);

		}
		me.consume();
	}

	@Override
	public void mouseReleased(final MouseEvent me) {
		if (location == null) {
			return;
		}
		location = null;
		final Object source = me.getSource();
		if (source instanceof EvpFigure) {
			final EvpFigure figure = (EvpFigure) source;
			figure.setBackgroundColor(ColorConstants.white);
		}
		me.consume();
	}

	@Override
	public void mouseEntered(final MouseEvent me) {
	}

	@Override
	public void mouseExited(final MouseEvent me) {
	}

	@Override
	public void mouseHover(final MouseEvent me) {
	}

	@Override
	public void mouseMoved(final MouseEvent me) {
	}

	@Override
	public void mouseDoubleClicked(final MouseEvent me) {
	}

}
