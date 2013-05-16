package og.basics.gui.checkbox;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class ToggleCheckbox extends JCheckBox {

	public ToggleCheckbox(final String text, final String tooltip) {
		super(text);
		setToolTipText(tooltip);
		initUI();
	}

	private void initUI() {
		// Set default icon for checkbox
		setIcon(new ImageIcon("icon.png"));
		// Set selected icon when checkbox state is selected
		setSelectedIcon(new ImageIcon("selectedIcon.png"));
		// Set disabled icon for checkbox
		setDisabledIcon(new ImageIcon("disabledIcon.png"));
		// Set disabled-selected icon for checkbox
		setDisabledSelectedIcon(new ImageIcon("disabledSelectedIcon.png"));
		// Set checkbox icon when checkbox is pressed
		setPressedIcon(new ImageIcon("pressedIcon.png"));
		// Set icon when a mouse is over the checkbox
		setRolloverIcon(new ImageIcon("rolloverIcon.png"));
		// Set icon when a mouse is over a selected checkbox
		setRolloverSelectedIcon(new ImageIcon("rolloverSelectedIcon.png"));
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
