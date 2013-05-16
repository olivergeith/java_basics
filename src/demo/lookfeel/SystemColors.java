package demo.lookfeel;

import java.awt.Color;
import java.awt.SystemColor;

public class SystemColors {

	public static void main(final String[] a) {
		final Color[] sysColor = new Color[] {
				SystemColor.activeCaption, SystemColor.activeCaptionBorder, SystemColor.activeCaptionText, SystemColor.control, SystemColor.controlDkShadow,
				SystemColor.controlHighlight, SystemColor.controlLtHighlight, SystemColor.controlShadow, SystemColor.controlText, SystemColor.desktop,
				SystemColor.inactiveCaption, SystemColor.inactiveCaptionBorder, SystemColor.inactiveCaptionText, SystemColor.info, SystemColor.infoText,
				SystemColor.menu, SystemColor.menuText, SystemColor.scrollbar, SystemColor.text, SystemColor.textHighlight, SystemColor.textHighlightText,
				SystemColor.textInactiveText, SystemColor.textText, SystemColor.window, SystemColor.windowBorder, SystemColor.windowText
		};

		for (final Color c : sysColor) {
			System.out.println(c.getRGB());

		}

	}

}