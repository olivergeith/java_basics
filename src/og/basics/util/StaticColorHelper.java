package og.basics.util;

import java.awt.Color;

public class StaticColorHelper {

	public static boolean isdark(final Color col) {
		float[] hsbValues = new float[3];

		hsbValues = Color.RGBtoHSB(col.getRed(), col.getGreen(), col.getBlue(), hsbValues);

		// final float hue = hsbValues[0];
		// final float saturation = hsbValues[1];
		final float brightness = hsbValues[2];
		// System.out.println(col + " Brightness =" + brightness);
		if (brightness < 0.7f)
			return true;
		else
			return false;
	}

}
