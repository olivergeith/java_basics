package deom.duffporter;

/*
 This program is a part of the companion code for Core Java 8th ed.
 (http://horstmann.com/corejava)

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This program demonstrates the Porter-Duff composition rules.
 * 
 * @version 1.03 2007-08-16
 * @author Cay Horstmann
 */
public class DuffPorter {
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				final JFrame frame = new CompositeTestFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

/**
 * This frame contains a combo box to choose a composition rule, a slider to
 * change the source alpha channel, and a component that shows the composition.
 */
class CompositeTestFrame extends JFrame {
	public CompositeTestFrame() {
		setTitle("CompositeTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		canvas = new CompositeComponent();
		add(canvas, BorderLayout.CENTER);

		ruleCombo = new JComboBox(new Object[] {
				new Rule("CLEAR", "  ", "  "), new Rule("SRC", " S", " S"), new Rule("DST", "  ", "DD"), new Rule("SRC_OVER", " S", "DS"),
				new Rule("DST_OVER", " S", "DD"), new Rule("SRC_IN", "  ", " S"), new Rule("SRC_OUT", " S", "  "), new Rule("DST_IN", "  ", " D"),
				new Rule("DST_OUT", "  ", "D "), new Rule("SRC_ATOP", "  ", "DS"), new Rule("DST_ATOP", " S", " D"), new Rule("XOR", " S", "D "),
		});
		ruleCombo.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				final Rule r = (Rule) ruleCombo.getSelectedItem();
				canvas.setRule(r.getValue());
				explanation.setText(r.getExplanation());
			}
		});

		alphaSlider = new JSlider(0, 100, 75);
		alphaSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(final ChangeEvent event) {
				canvas.setAlpha(alphaSlider.getValue());
			}
		});
		final JPanel panel = new JPanel();
		panel.add(ruleCombo);
		panel.add(new JLabel("Alpha"));
		panel.add(alphaSlider);
		add(panel, BorderLayout.NORTH);

		explanation = new JTextField();
		add(explanation, BorderLayout.SOUTH);

		canvas.setAlpha(alphaSlider.getValue());
		final Rule r = (Rule) ruleCombo.getSelectedItem();
		canvas.setRule(r.getValue());
		explanation.setText(r.getExplanation());
	}

	private final CompositeComponent	canvas;
	private final JComboBox				ruleCombo;
	private final JSlider				alphaSlider;
	private final JTextField			explanation;
	private static final int			DEFAULT_WIDTH	= 400;
	private static final int			DEFAULT_HEIGHT	= 400;
}

/**
 * This class describes a Porter-Duff rule.
 */
class Rule {
	/**
	 * Constructs a Porter-Duff rule
	 * 
	 * @param n
	 *            the rule name
	 * @param pd1
	 *            the first row of the Porter-Duff square
	 * @param pd2
	 *            the second row of the Porter-Duff square
	 */
	public Rule(final String n, final String pd1, final String pd2) {
		name = n;
		porterDuff1 = pd1;
		porterDuff2 = pd2;
	}

	/**
	 * Gets an explanation of the behavior of this rule.
	 * 
	 * @return the explanation
	 */
	public String getExplanation() {
		final StringBuilder r = new StringBuilder("Source ");
		if (porterDuff2.equals("  "))
			r.append("clears");
		if (porterDuff2.equals(" S"))
			r.append("overwrites");
		if (porterDuff2.equals("DS"))
			r.append("blends with");
		if (porterDuff2.equals(" D"))
			r.append("alpha modifies");
		if (porterDuff2.equals("D "))
			r.append("alpha complement modifies");
		if (porterDuff2.equals("DD"))
			r.append("does not affect");
		r.append(" destination");
		if (porterDuff1.equals(" S"))
			r.append(" and overwrites empty pixels");
		r.append(".");
		return r.toString();
	}

	@Override
	public String toString() {
		return name;
	}

	/**
	 * Gets the value of this rule in the AlphaComposite class
	 * 
	 * @return the AlphaComposite constant value, or -1 if there is no matching
	 *         constant.
	 */
	public int getValue() {
		try {
			return (Integer) AlphaComposite.class.getField(name).get(null);
		} catch (final Exception e) {
			return -1;
		}
	}

	private final String	name;
	private final String	porterDuff1;
	private final String	porterDuff2;
}

/**
 * This component draws two shapes, composed with a composition rule.
 */
class CompositeComponent extends JComponent {
	public CompositeComponent() {
		shape1 = new Ellipse2D.Double(100, 100, 150, 100);
		shape2 = new Rectangle2D.Double(150, 150, 150, 100);
	}

	@Override
	public void paintComponent(final Graphics g) {
		final Graphics2D g2 = (Graphics2D) g;

		final BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		final Graphics2D gImage = image.createGraphics();
		gImage.setPaint(Color.red);
		// gImage.setPaint(new Color(64, 64, 64, 10));
		gImage.fill(shape1);
		final AlphaComposite composite = AlphaComposite.getInstance(rule, alpha);
		gImage.setComposite(composite);
		// gImage.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN,
		// 255));
		gImage.setPaint(Color.blue);
		gImage.fill(shape2);
		g2.drawImage(image, null, 0, 0);
	}

	/**
	 * Sets the composition rule.
	 * 
	 * @param r
	 *            the rule (as an AlphaComposite constant)
	 */
	public void setRule(final int r) {
		rule = r;
		repaint();
	}

	/**
	 * Sets the alpha of the source
	 * 
	 * @param a
	 *            the alpha value between 0 and 100
	 */
	public void setAlpha(final int a) {
		alpha = a / 100.0F;
		repaint();
	}

	private int			rule;
	private final Shape	shape1;
	private final Shape	shape2;
	private float		alpha;
}
