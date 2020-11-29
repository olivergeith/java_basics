package swt.examples.plugin.draw2d.example;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import swt.examples.plugin.draw2d.example.data.ModTabElement;
import swt.examples.plugin.draw2d.example.figure.EvpFigure;
import swt.examples.plugin.draw2d.example.figure.RecttangleDecoration;

public class EvpController {

	private final Figure root;

	Map<ModTabElement, EvpFigure> evpFigures = new HashMap<>();

	private final MouseDragListenerRaster mousListener;

	public EvpController(final Figure root) {
		this.root = root;
		mousListener = new MouseDragListenerRaster(this);

	}

	/** This method draws figures on the root */
	public void draw() {

		final EvpFigure ele1 = addEvpFigure(new ModTabElement("Signal", "49P1"));
		final EvpFigure ele2 = addEvpFigure(new ModTabElement("Gleis", "GL101"));
		final EvpFigure ele3 = addEvpFigure(new ModTabElement("Weiche", "W01"));
		final EvpFigure ele4 = addEvpFigure(new ModTabElement("Gleis", "GL102"));
		final EvpFigure ele5 = addEvpFigure(new ModTabElement("Gleis", "GL103"));

		root.add(ele1, new Rectangle(new Point(10, 10), ele1.getPreferredSize()));
		root.add(ele2, new Rectangle(new Point(110, 10), ele2.getPreferredSize()));
		root.add(ele3, new Rectangle(new Point(210, 10), ele3.getPreferredSize()));
		root.add(ele4, new Rectangle(new Point(310, 10), ele4.getPreferredSize()));
		root.add(ele5, new Rectangle(new Point(310, 80), ele5.getPreferredSize()));

		root.add(addConnection(ele1, ele2));
		root.add(addConnection(ele2, ele3));
		root.add(addConnection(ele3, ele4));
		root.add(addConnection(ele3, ele5));
	}

	public Connection addConnection(final EvpFigure fig1, final EvpFigure fig2) {
		final PolylineConnection conn = new PolylineConnection();
		conn.setSourceAnchor(new ChopboxAnchor(fig1));
		conn.setTargetAnchor(new ChopboxAnchor(fig2));
		conn.setSourceDecoration(new RecttangleDecoration("Gs1"));
		conn.setTargetDecoration(new RecttangleDecoration("Gs2"));

//		final ConnectionEndpointLocator sourceEL = new ConnectionEndpointLocator(conn, false);
//		sourceEL.setVDistance(10);
//		sourceEL.setUDistance(0);
//		final Label labelSource = new Label("Gs1");
//		conn.add(labelSource, sourceEL);
//
//		final ConnectionEndpointLocator targetEL = new ConnectionEndpointLocator(conn, true);
//		targetEL.setVDistance(10);
//		targetEL.setUDistance(0);
//		final Label labelTarget = new Label("Gs2");
//		conn.add(labelTarget, targetEL);

		return conn;
	}

	public EvpFigure addEvpFigure(final ModTabElement evpElement) {
		final EvpFigure evpFigure = new EvpFigure(evpElement);
		evpFigures.put(evpElement, evpFigure);
		evpFigure.addMouseListener(mousListener);
		evpFigure.addMouseMotionListener(mousListener);
		return evpFigure;
	}

	public void removeEvpFigure(final ModTabElement evpElement) {
		final EvpFigure evpFigure = evpFigures.get(evpElement);
		final IFigure parent = evpFigure.getParent();
		parent.remove(evpFigure);
	}

	public long otherFiguresAtPosition(final Point position, final Figure figure) {
		return evpFigures.values().stream().filter(fig -> !fig.equals(figure) && fig.containsPoint(position)).count();

	}

	public org.eclipse.swt.graphics.Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new org.eclipse.swt.graphics.Rectangle(0, 0, 1980, 1200);
	}

}
