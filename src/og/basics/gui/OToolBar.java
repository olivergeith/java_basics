package og.basics.gui;

import java.awt.Component;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * Diese Klasse erweitert den JToolBar um ein neue Methode So ist es einfach
 * m�glich einem Button Icon "UND" Text zu geben
 * 
 * @author Geith
 * 
 */
public class OToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;

	/**
	 * Die Normale Methode add(Action) legt einen Button nur mit einem Icon an.
	 * Der ActionName wird verschluckt! Hier kann man nun �ber das Boolean
	 * w�hlen, ob mit oder ohne Text.
	 * 
	 * @param a
	 *            Die Aktion
	 * @param withText
	 * @return den JButton, der den ToolBar hinzugef�gt wurde
	 */
	public JButton add(Action a, boolean withText) {
		JButton b = super.add(a);
		if (withText) {
			// test aus der Aktion holen
			Object o = a.getValue(Action.NAME);
			if (o instanceof String)
				b.setText((String) o);
		}
		return b;
	}

	/**
	 * Disabeld alle Buttons auf einmal! (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#setEnabled(boolean)
	 */
	@Override
	public void setEnabled(boolean arg0) {
		Component[] c = getComponents();
		for (int i = 0; i < c.length; i++) {
			c[i].setEnabled(arg0);
		}
		super.setEnabled(arg0);
	}

}
