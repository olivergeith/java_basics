package og.basics.gui.Jcolorselectbutton;

import java.awt.Color;
import java.awt.GridLayout;

public class KrozCrayonPanel extends CrayonPanel {
	private static final long	serialVersionUID	= 1L;

	@Override
	protected void buildChooser() {
		setLayout(new GridLayout(0, 1));

		createCrayon("Kroz HueLite", new Color(0x33b5e5));
		createCrayon("Kroz HueBlue", new Color(0x3388ff));
		createCrayon("Kroz HueCyan", new Color(0x33e5e5));
		createCrayon("Kroz HueBrown", new Color(0xaa8855));
		createCrayon("Kroz HueGreen", new Color(0x99cc00));
		createCrayon("Kroz HueOrange", new Color(0xff8800));
		createCrayon("Kroz HuePink", new Color(0xffaacc));
		createCrayon("Kroz HuePurple", new Color(0xaa66cc));
	}

	@Override
	public String getDisplayName() {
		return "Kroz Hue Theme Colors";
	}

}
