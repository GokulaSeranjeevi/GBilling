package com.skywings.gbilling.common;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import org.springframework.stereotype.Component;

@Component
public class JTextFieldComponent {

	public void textLength(JTextField txtOperName, int i) {

		int maxLength = i;

		PlainDocument doc = (PlainDocument) txtOperName.getDocument();
		doc.setDocumentFilter(new DocumentFilter() {
			@Override
			public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
					throws BadLocationException {
				if (fb.getDocument().getLength() + string.length() <= maxLength) {
					super.insertString(fb, offset, string, attr);
				}
			}

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				if (fb.getDocument().getLength() + text.length() - length <= maxLength) {
					super.replace(fb, offset, length, text, attrs);
				}
			}
		});
	}

}
