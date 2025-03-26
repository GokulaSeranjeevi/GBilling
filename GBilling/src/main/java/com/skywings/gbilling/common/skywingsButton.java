package com.skywings.gbilling.common;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;

public class skywingsButton extends JButton implements FocusListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private Color hoverBackgroundColor;
	private Color pressedBackgroundColor;

	public skywingsButton() {
		this.initialize();
	}

	public skywingsButton(Action arg0) {
		super(arg0);
		this.initialize();
	}

	public skywingsButton(Icon arg0) {
		super(arg0);
		this.initialize();
	}

	public skywingsButton(String arg0) {
		super(arg0);
		this.initialize();
	}

	public skywingsButton(String arg0, Icon arg1) {
		super(arg0, arg1);
		this.initialize();
	}

	private void initialize() {
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.addFocusListener(this);
		this.addMouseListener(this);
	}

	protected void paintComponent(Graphics g) {
		if (this.getModel().isPressed()) {
			if (this.pressedBackgroundColor != null) {
				g.setColor(this.pressedBackgroundColor);
			}
		} else if (this.getModel().isRollover()) {
			if (this.hoverBackgroundColor != null) {
				g.setColor(this.hoverBackgroundColor);
			}
		} else {
			g.setColor(this.getBackground());
		}

		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		super.paintComponent(g);
	}

	public Color getHoverBackgroundColor() {
		return this.hoverBackgroundColor;
	}

	public void setHoverBackgroundColor(Color hoverBackgroundColor) {
		this.hoverBackgroundColor = hoverBackgroundColor;
	}

	public Color getPressedBackgroundColor() {
		return this.pressedBackgroundColor;
	}

	public void setPressedBackgroundColor(Color pressedBackgroundColor) {
		this.pressedBackgroundColor = pressedBackgroundColor;
	}

	public void setBackground(Color bg) {
		this.pressedBackgroundColor = bg;
		this.hoverBackgroundColor = bg;
		super.setBackground(bg);
	}

	public void focusGained(FocusEvent e) {
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
				BorderFactory.createLineBorder(Color.WHITE, 1)));
	}

	public void focusLost(FocusEvent e) {
		this.setBorder(BorderFactory.createCompoundBorder());
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
				BorderFactory.createLineBorder(Color.WHITE, 1)));
	}

	public void mouseExited(MouseEvent arg0) {
		if (!this.isFocusOwner()) {
			this.setBorder(BorderFactory.createCompoundBorder());
		}

	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}
}
