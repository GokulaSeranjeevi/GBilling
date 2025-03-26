package com.skywings.gbilling.common;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class SkywingsFont {
	private Font fontcalibri;
	private Font fontcalibrib;

	public SkywingsFont() {
		InputStream inputStream = SkywingsFont.class.getClassLoader().getResourceAsStream("calibri.ttf");
		InputStream inputStreamb = SkywingsFont.class.getClassLoader().getResourceAsStream("calibrib.ttf");

		try {
			this.fontcalibri = Font.createFont(0, inputStream);
			this.fontcalibrib = Font.createFont(0, inputStreamb);
		} catch (FontFormatException var4) {
		} catch (IOException var5) {
			var5.printStackTrace();
		}

	}

	/*
	 * public Font getFont(SkywingsFont.FontStyle fontStyle, int fontSize) { Font
	 * font; if (SkywingsFont.FontStyle.BOLD == fontStyle) { if (null ==
	 * this.fontcalibri) { }
	 * 
	 * font = this.fontcalibri.deriveFont(1,
	 * Float.parseFloat(String.valueOf(fontSize))); } else if
	 * (SkywingsFont.FontStyle.PLAIN == fontStyle) { if (null == this.fontcalibri) {
	 * }
	 * 
	 * font = this.fontcalibri.deriveFont(0,
	 * Float.parseFloat(String.valueOf(fontSize))); } else { font =
	 * this.fontcalibri.deriveFont(Float.parseFloat(String.valueOf(fontSize))); }
	 * 
	 * return font; }
	 *
	 * 
	 */
	public Font getFont(SkywingsFont.FontStyle fontStyle, int fontSize) {
		// Ensure fontcalibri is not null, if it is, initialize it to a default font
		if (this.fontcalibri == null) {
			this.fontcalibri = new Font("Calibri", Font.PLAIN, 12); // Default font initialization
		}

		Font font;

		if (SkywingsFont.FontStyle.BOLD == fontStyle) {
			// If font is BOLD, derive the font in bold style
			font = this.fontcalibri.deriveFont(Font.BOLD, (float) fontSize);
		} else if (SkywingsFont.FontStyle.PLAIN == fontStyle) {
			// If font is PLAIN, derive the font in plain style
			font = this.fontcalibri.deriveFont(Font.PLAIN, (float) fontSize);
		} else {
			// For any other style (like italic, etc.), just derive the font with the
			// specified size
			font = this.fontcalibri.deriveFont((float) fontSize);
		}

		return font;
	}

	public Font getFont(int fontSize) {
		if (null == this.fontcalibri) {
		} else {
			Font font = this.fontcalibri.deriveFont(Float.parseFloat(String.valueOf(fontSize)));
			return font;
		}
		return fontcalibri;
	}

	public static enum FontStyle {
		PLAIN, BOLD;

		// $FF: synthetic method
		private static SkywingsFont.FontStyle[] $values() {
			return new SkywingsFont.FontStyle[] { PLAIN, BOLD };
		}
	}
}
