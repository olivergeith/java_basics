package og.basics.gui.Jcolorselectbutton;

import java.awt.Color;
import java.awt.GridLayout;

public class MorphologyCrayonPanel extends CrayonPanel {
	private static final long	serialVersionUID	= 1L;

	@Override
	protected void buildChooser() {
		setLayout(new GridLayout(0, 1));

		createCrayon("Morphology Red", new Color(0xff0019));
		createCrayon("Morphology Green", new Color(0xbbfa00));
		createCrayon("Morphology Neon Green", new Color(0x03fc03));
		createCrayon("Morphology Purple", new Color(0x8e00fa));
		createCrayon("Morphology Pink", new Color(0xfa14F2));
		createCrayon("Morphology Yellow", new Color(0xffec20));
		createCrayon("Morphology Blue", new Color(0x33b5e5));
		createCrayon("Morphology Orange", new Color(0xffa600));
	}

	@Override
	public String getDisplayName() {
		return "Morphology Colors";
	}

}
