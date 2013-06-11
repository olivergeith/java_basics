package og.basics.gui.Jcolorselectbutton;

import java.awt.Color;
import javax.swing.ImageIcon;

public class KrozCrayonPanel extends CrayonPanel {
	private static final long		serialVersionUID	= 1L;

	private static final ImageIcon	icon				= new ImageIcon(CrayonPanel.class.getResource("kroz.png"));

	@Override
	protected void addColors() {
		createCrayon("Kroz HueLite", new Color(0x33b5e5));
		createCrayon("Kroz HueRed", new Color(0xff4444));
		createCrayon("Kroz HueBlue", new Color(0x3388ff));
		createCrayon("Kroz HueCyan", new Color(0x33e5e5));
		createCrayon("Kroz HueBrown", new Color(0xaa8855));
		createCrayon("Kroz HueGreen", new Color(0x99cc00));
		createCrayon("Kroz HueOrange", new Color(0xff8800));
		createCrayon("Kroz HuePink", new Color(0xffaacc));
		createCrayon("Kroz HuePurple", new Color(0xaa66cc));
		createCrayon("Kroz HueSilver", new Color(0xcccccc));
		createCrayon("Kroz HueYellow", new Color(0xffff00));
	}

	@Override
	public String getDisplayName() {
		return "Kroz Hue Theme Colors";
	}

	@Override
	protected ImageIcon getIcon() {
		return icon;
	}

}
