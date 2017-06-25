package demo.kochsnowflake;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CircSpiral extends JPanel {
	private static final int DELTA = 1;
	private static final int ARC_ANGLE = 20;
	private static final int PREF_W = 300;
	private static final int PREF_H = PREF_W;
	private static final int LOOP_MAX = 400;

	@Override
	public void paintComponent(final Graphics g) {
		int x = PREF_W / 2;
		int y = PREF_H / 2;
		int width = 1;
		int height = 1;
		int startAngle = 0;
		final int arcAngle = ARC_ANGLE;
		for (int i = 0; i < LOOP_MAX; i++) {
			g.drawArc(x, y, width, height, startAngle, arcAngle);
			x = x - DELTA;
			y = y - DELTA;
			width += 2 * DELTA;
			height += 2 * DELTA;
			startAngle = startAngle - arcAngle;
		}
	}

	@Override
	public Dimension getPreferredSize() {
		if (isPreferredSizeSet()) {
			return super.getPreferredSize();
		}
		return new Dimension(PREF_W, PREF_H);
	}

	public static void main(final String[] args) {
		final CircSpiral panel = new CircSpiral();
		final JFrame application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.pack();
		application.setLocationRelativeTo(null);
		application.setVisible(true);
	}
}