package og.basics.gui.lookandfeel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class LookendfeelCombobox extends JComboBox<String> {

	/**
	 * TODO comment the serialVersionUID
	 */
	private static final long	serialVersionUID	= -7046798065980671298L;

	public LookendfeelCombobox() {
		super();
		loadLooks();
	}

	private void loadLooks() {
		final UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
		for (final UIManager.LookAndFeelInfo look : looks) {
			addItem(look.getClassName());
		}
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				final String finalLafClassName = (String) getSelectedItem();
				try {
					UIManager.setLookAndFeel(finalLafClassName);
					SwingUtilities.updateComponentTreeUI(LookendfeelCombobox.this.getRootPane());
				} catch (final Exception exception) {
					JOptionPane.showMessageDialog(LookendfeelCombobox.this, "Can't change look and feel", "Invalid PLAF", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}

	/**
	 * TODO comment main
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		// TODO Auto-generated method stub

	}

}
