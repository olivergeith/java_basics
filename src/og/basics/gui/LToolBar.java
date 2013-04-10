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
public class LToolBar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5972181516046457075L;

	/**
	 * Die Normale Methode add(Action) legt einen Button nur mit einem Icon an.
	 * D er ActionName wird verschluckt! Hier kann man nun �ber das Boolean
	 * w�hlen, ob mit oder ohne Text.
	 * 
	 * @param a
	 *            Die Aktion
	 * @param withText
	 * @return den JButton, der den ToolBar hinzugef�gt wurde
	 */
	public JButton add(final Action a, final boolean withText) {
		final JButton b = super.add(a);
		if (withText) {
			// test aus der Aktion holen
			final Object o = a.getValue(Action.NAME);
			if (o instanceof String)
				b.setText((String) o);
		}
		return b;
	}

	@Override
	public void setEnabled(final boolean arg0) {
		final Component[] c = getComponents();
		for (int i = 0; i < c.length; i++) {
			c[i].setEnabled(arg0);
		}
		super.setEnabled(arg0);
	}

}
