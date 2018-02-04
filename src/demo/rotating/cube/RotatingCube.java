package demo.rotating.cube;

import static java.lang.Math.PI;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.round;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class RotatingCube extends JPanel {
	double[][] nodes = { { -1, -1, -1 }, { -1, -1, 1 }, { -1, 1, -1 }, { -1, 1, 1 }, { 1, -1, -1 }, { 1, -1, 1 }, { 1, 1, -1 }, { 1, 1, 1 } };

	int[][] edges = { { 0, 1 }, { 1, 3 }, { 3, 2 }, { 2, 0 }, { 4, 5 }, { 5, 7 }, { 7, 6 }, { 6, 4 }, { 0, 4 }, { 1, 5 }, { 2, 6 }, { 3, 7 } };

	public RotatingCube() {
		setPreferredSize(new Dimension(640, 640));
		setBackground(Color.white);

		scale(100);
		rotateCube(PI / 4, atan(sqrt(2)));

		new Timer(17, (final ActionEvent e) -> {
			rotateCube(PI / 180, PI / 60);
			repaint();
		}).start();
	}

	final void scale(final double s) {
		for (final double[] node : nodes) {
			node[0] *= s;
			node[1] *= s;
			node[2] *= s;
		}
	}

	final void rotateCube(final double angleX, final double angleY) {
		final double sinX = sin(angleX);
		final double cosX = cos(angleX);

		final double sinY = sin(angleY);
		final double cosY = cos(angleY);

		for (final double[] node : nodes) {
			final double x = node[0];
			final double y = node[1];
			double z = node[2];

			node[0] = x * cosX - z * sinX;
			node[2] = z * cosX + x * sinX;

			z = node[2];

			node[1] = y * cosY - z * sinY;
			node[2] = z * cosY + y * sinY;
		}
	}

	void drawCube(final Graphics2D g) {
		g.translate(getWidth() / 2, getHeight() / 2);

		for (final int[] edge : edges) {
			final double[] xy1 = nodes[edge[0]];
			final double[] xy2 = nodes[edge[1]];
			g.drawLine((int) round(xy1[0]), (int) round(xy1[1]), (int) round(xy2[0]), (int) round(xy2[1]));
		}

		for (final double[] node : nodes) {
			g.fillOval((int) round(node[0]) - 4, (int) round(node[1]) - 4, 8, 8);
		}
	}

	@Override
	public void paintComponent(final Graphics gg) {
		super.paintComponent(gg);
		final Graphics2D g = (Graphics2D) gg;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		drawCube(g);
	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> {
			final JFrame f = new JFrame();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setTitle("Rotating Cube");
			f.setResizable(false);
			f.add(new RotatingCube(), BorderLayout.CENTER);
			f.pack();
			f.setLocationRelativeTo(null);
			f.setVisible(true);
		});
	}
}