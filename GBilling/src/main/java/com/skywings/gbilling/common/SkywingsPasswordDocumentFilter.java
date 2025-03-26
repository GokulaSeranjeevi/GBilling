package com.skywings.gbilling.common;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

class SkywingsPasswordDocumentFilter extends DocumentFilter {
	private SkywingsPasswordField skywingsPasswordField;

	SkywingsPasswordDocumentFilter(SkywingsPasswordField textField) {
		this.skywingsPasswordField = textField;
	}

	public void insertString(FilterBypass arg0, int arg1, String arg2, AttributeSet arg3) throws BadLocationException {
		super.insertString(arg0, arg1, arg2, arg3);
	}

	public void remove(FilterBypass arg0, int arg1, int arg2) throws BadLocationException {
		super.remove(arg0, arg1, arg2);
	}

	public void replace(FilterBypass arg0, int offs, int length, String text, AttributeSet arg4)
			throws BadLocationException {
		String reqText = text;
		if (this.skywingsPasswordField.getMaxLength() > 0) {
			if (offs >= this.skywingsPasswordField.getMaxLength()) {
				reqText = "";
			} else if (offs < this.skywingsPasswordField.getMaxLength()) {
				int intReqChar = this.skywingsPasswordField.getMaxLength() - offs;
				if (intReqChar < text.length()) {
					reqText = text.substring(0, intReqChar);
				}
			}
		}

		super.replace(arg0, offs, length, reqText, arg4);
	}
}
