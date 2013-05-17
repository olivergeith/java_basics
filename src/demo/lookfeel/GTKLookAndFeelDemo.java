package demo.lookfeel;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;

public class GTKLookAndFeelDemo {
	public static void main(final String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		final JLabel label = new JLabel("Label");
		final JTextField field = new JTextField("www.java2s.com!");
		final JList list = new JList(new String[] {
				"A", "B", "C"
		});
		final JScrollPane listPane = new JScrollPane(list);
		listPane.setPreferredSize(new Dimension(250, 100));

		final JScrollPane treePane = new JScrollPane(new JTree());
		treePane.setPreferredSize(new Dimension(250, 100));
		final JButton button = new JButton("Click me");

		final JPanel cp = new JPanel();
		cp.add(label);
		cp.add(field);
		cp.add(listPane);
		cp.add(treePane);
		cp.add(button);

		final JFrame frame = new JFrame();
		frame.setTitle("Windows Look and Feel Demo");
		frame.setPreferredSize(new Dimension(280, 300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(cp);
		frame.pack();
		frame.setVisible(true);

	}
}