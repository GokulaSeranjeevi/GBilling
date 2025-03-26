package com.skywings.gbilling.common;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class SkywingsComboSearch<E> extends PlainDocument {
	private static final long serialVersionUID = 1L;
	private JComboBox<E> thisCombo;
	private transient ComboBoxModel<E> thisModel;
	private JTextComponent txtEditor;
	private boolean selecting = false;

	public SkywingsComboSearch(JComboBox<E> comboBox) {
		this.thisCombo = comboBox;
		this.thisModel = this.thisCombo.getModel();
		this.txtEditor = (JTextComponent) this.thisCombo.getEditor().getEditorComponent();
		comboBox.addActionListener((a) -> {
			if (!this.selecting) {
				this.highlightCompletedText(0);
			}

		});
		this.txtEditor.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (SkywingsComboSearch.this.thisCombo.isDisplayable()) {
					SkywingsComboSearch.this.thisCombo.setPopupVisible(true);
				}

				if (e.getKeyCode() == 27) {
					SkywingsComboSearch.this.thisCombo.setPopupVisible(false);
				}

			}
		});
		this.txtEditor.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (SkywingsComboSearch.this.thisCombo.isDisplayable()) {
					SkywingsComboSearch.this.thisCombo.setPopupVisible(true);
				}

			}

			public void focusLost(FocusEvent e) {
				SkywingsComboSearch.this.thisCombo.setPopupVisible(false);
			}
		});
		Object selected = this.thisCombo.getSelectedItem();
		if (selected != null) {
			this.setText(selected.toString());
		}

		this.highlightCompletedText(0);
	}

	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		int offset = offs;
		if (!this.selecting) {
			super.insertString(offs, str, a);
			Object item = this.lookupItem(this.getText(0, this.getLength()));
			if (item != null) {
				this.setSelectedItem(item);
			} else {
				if (this.thisCombo.getItemCount() > 0) {
					item = this.thisCombo.getSelectedItem();
				}

				offset = offs - str.length();
				this.thisCombo.getToolkit().beep();
			}

			if (item != null && !"".equals(item)) {
				this.setText(item.toString());
			}

			if (this.thisCombo.getItemCount() > 0) {
				this.highlightCompletedText(offset + str.length());
			}

		}
	}

	private void setSelectedItem(Object item) {
		this.selecting = true;
		this.thisModel.setSelectedItem(item);
		this.selecting = false;
	}

	private void setText(String text) {
		try {
			super.remove(0, this.getLength());
			super.insertString(0, text, (AttributeSet) null);
		} catch (BadLocationException var3) {
			throw new RuntimeException(var3);
		}
	}

	private void highlightCompletedText(int start) {
		Object selected;
		if (this.txtEditor.getText().trim().length() <= 0) {
			this.thisModel = this.thisCombo.getModel();
			this.txtEditor = (JTextComponent) this.thisCombo.getEditor().getEditorComponent();
			selected = this.thisCombo.getSelectedItem();
			if (selected != null) {
				this.setText(selected.toString());
			}
		}

		selected = this.thisCombo.getSelectedItem();
		String text = "";
		if (selected != null) {
			this.setText(selected.toString());
			text = selected.toString();
		}

		if (text.length() > 0) {
			this.txtEditor.setCaretPosition(text.length());
			this.txtEditor.moveCaretPosition(start);
		}

	}

	private Object lookupItem(String pattern) {
		Object selectedItem = this.thisModel.getSelectedItem();
		if (selectedItem != null && this.startsWithIgnoreCase(selectedItem.toString(), pattern)) {
			return selectedItem;
		} else {
			int i = 0;

			for (int n = this.thisModel.getSize(); i < n; ++i) {
				Object currentItem = this.thisModel.getElementAt(i);
				if (this.startsWithIgnoreCase(currentItem.toString(), pattern)) {
					return currentItem;
				}
			}

			return pattern;
		}
	}

	private boolean startsWithIgnoreCase(String str1, String str2) {
		return str1.toUpperCase().startsWith(str2.toUpperCase());
	}
}