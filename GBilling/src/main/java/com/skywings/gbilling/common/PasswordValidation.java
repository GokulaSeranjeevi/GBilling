package com.skywings.gbilling.common;

public class PasswordValidation {
	private PasswordValidation() {
	}

	public static String encript(String strPass) throws Exception {
		try {
			StringBuilder strText = new StringBuilder();
			String strValue = "";

			for (int i = 0; i < strPass.length(); ++i) {
				char character = strPass.charAt(i);
				float asciiValue = (float) character;
				char chrEncript = (char) Math.round(asciiValue * 5.0F / 3.0F);
				strText = strText.append(chrEncript);
			}

			strValue = String.valueOf(strText.reverse());
			return strValue;
		} catch (ArithmeticException var7) {
			throw new Exception(var7.getMessage(), var7);
		} catch (ArrayIndexOutOfBoundsException var8) {
			throw new Exception(var8.getMessage(), var8);
		}
	}

	public static String decript(String strPass) throws Exception {
		try {
			StringBuilder strText = new StringBuilder();
			String strValue = "";

			for (int i = 0; i < strPass.length(); ++i) {
				char character = strPass.charAt(i);
				float asciiValue = (float) character;
				char chrDecript = (char) Math.round(asciiValue * 3.0F / 5.0F);
				strText = strText.append(chrDecript);
			}

			strValue = String.valueOf(strText.reverse());
			return strValue;
		} catch (ArrayIndexOutOfBoundsException var7) {
			throw new Exception(var7.getMessage(), var7);
		} catch (ArithmeticException var8) {
			throw new Exception(var8.getMessage(), var8);
		}
	}
}
