package com.skywings.gbilling.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

public class SkywingsComboBox<E> extends JComboBox<E> {
	private static final long serialVersionUID = 1L;
	private JTextComponent txtEditor;
	private List<ListItem> listItem = new ArrayList();
	private Color color;

	public SkywingsComboBox() {
		this.color = Color.WHITE;
		this.txtEditor = (JTextComponent) this.getEditor().getEditorComponent();
		this.txtEditor.setDocument(new SkywingsComboSearch(this));
	}

	public void setEditorEditable(boolean bln) {
		if (this.txtEditor != null) {
			this.txtEditor.setEditable(bln);
		}

	}

	public SkywingsComboBox(E[] arg0) {
		super(arg0);
		this.color = Color.WHITE;
		this.txtEditor = (JTextComponent) this.getEditor().getEditorComponent();
		this.txtEditor.setDocument(new SkywingsComboSearch(this));
	}

	public SkywingsComboBox(ComboBoxModel<E> arg0) {
		super(arg0);
		this.color = Color.WHITE;
		this.txtEditor = (JTextComponent) this.getEditor().getEditorComponent();
		this.txtEditor.setDocument(new SkywingsComboSearch(this));
	}

	public void setNonEditableColor(Color color) {
		this.setRenderer(new SkywingsComboBox.MyRenderer(color));
		this.setColor(color);
	}

	public void setEditorBackground(Color color) {
		if (this.txtEditor != null) {
			this.txtEditor.setBackground(color);
			this.txtEditor.setOpaque(true);
		}

	}

	public void removeArrowButton() {
		Component[] components = this.getComponents();
		Component[] var2 = components;
		int var3 = components.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			Component component = var2[var4];
			if (component instanceof AbstractButton) {
				this.remove(component);
			}
		}

	}

	public void setListItem(List<ListItem> mListItem) {
		this.removeAllItems();
		this.listItem.clear();
		this.listItem = mListItem;
		if (this.listItem != null) {
			Iterator var2 = this.listItem.iterator();

			while (var2.hasNext()) {
				ListItem item = (ListItem) var2.next();
				this.addItem((E) item.getText());
			}
		}

	}

	public List<ListItem> getListItem() {
		return this.listItem;
	}

	public void addListItem(ListItem mListItem) {
		if (this.listItem == null || this.listItem.isEmpty()) {
			this.listItem = new ArrayList();
		}

		if (mListItem != null) {
			this.listItem.add(mListItem);
			this.addItem((E) mListItem.getText());
		}

	}

	public ListItem getListItem(int position) throws ArrayIndexOutOfBoundsException {
		try {
			return this.listItem != null && !this.listItem.isEmpty() ? (ListItem) this.listItem.get(position) : null;
		} catch (ArrayIndexOutOfBoundsException var3) {
			throw var3;
		}
	}

	public Object getSelectedItemValue() throws ArrayIndexOutOfBoundsException {
		try {
			if (this.listItem != null && !this.listItem.isEmpty()) {
				return this.getSelectedIndex() < 0 ? null
						: ((ListItem) this.listItem.get(this.getSelectedIndex())).getValue();
			} else {
				return null;
			}
		} catch (ArrayIndexOutOfBoundsException var2) {
			throw var2;
		}
	}

	public boolean setSelectedItemValue(Object selectedValue) throws ArrayIndexOutOfBoundsException {
		try {
			if (this.listItem != null && !this.listItem.isEmpty()) {
				Iterator var2 = this.listItem.iterator();

				ListItem item;
				do {
					if (!var2.hasNext()) {
						return false;
					}

					item = (ListItem) var2.next();
				} while (!item.getValue().equals(selectedValue));

				this.setSelectedIndex(this.listItem.indexOf(item));
				return true;
			} else {
				return false;
			}
		} catch (ArrayIndexOutOfBoundsException var4) {
			throw var4;
		}
	}

	public void removeAllItems() {
		super.removeAllItems();
		this.listItem.clear();
	}

	public void removeItem(Object arg0) {
		super.removeItem(arg0);
		this.listItem.remove(arg0);
	}

	public void removeItemAt(int arg0) {
		super.removeItemAt(arg0);
		this.listItem.remove(arg0);
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	class MyRenderer extends DefaultListCellRenderer {
		private static final long serialVersionUID = 1L;
		private Color color;

		public MyRenderer(Color color) {
			this.color = Color.WHITE;
			this.color = color;
		}

		public void setBorder(Border border) {
			super.setBorder(BorderFactory.createLineBorder(this.color, 0));
		}

		public void setBackground(Color bg) {
			super.setBackground(this.color);
		}

		public Insets insets() {
			return new Insets(0, 0, 0, 0);
		}
	}
}
