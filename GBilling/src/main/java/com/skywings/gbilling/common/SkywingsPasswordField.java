package com.skywings.gbilling.common;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;

public class SkywingsPasswordField extends JPasswordField implements KeyListener, FocusListener {
	private static final long serialVersionUID = 1L;
	private int maxLength = 0;

	public SkywingsPasswordField() {
		this.initialize();
	}

	public SkywingsPasswordField(int arg0) {
		super(arg0);
		this.initialize();
	}

	public SkywingsPasswordField(String arg0) {
		super(arg0);
		this.initialize();
	}

	public SkywingsPasswordField(String arg0, int arg1) {
		super(arg0, arg1);
		this.initialize();
	}

	public SkywingsPasswordField(Document arg0, String arg1, int arg2) {
		super(arg0, arg1, arg2);
		this.initialize();
	}

	private void initialize() {
		this.addKeyListener(this);
		this.addFocusListener(this);
		((AbstractDocument) this.getDocument()).setDocumentFilter(new SkywingsPasswordDocumentFilter(this));
		this.setBorder(BorderFactory.createEtchedBorder());
	}

	public int getMaxLength() {
		return this.maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
		if (this.maxLength > 0 && this.getPassword().length >= this.maxLength && this.getSelectedText() == null) {
			e.consume();
		}
	}

	public void focusGained(FocusEvent arg0) {
		this.setSelectionStart(0);
		this.setSelectionEnd(this.getPassword().length);
	}

	public void focusLost(FocusEvent arg0) {
		this.setSelectionStart(0);
		this.setSelectionEnd(0);
	}
}
