package com.skywings.gbilling.common;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ImageLoad {

	public void load(JMenu mnuMaster) {
		// TODO Auto-generated method stub
		ImageIcon icon = new ImageIcon("D:\\Gokul\\GBilling\\src\\main\\resources\\resource\\attendance.png");

		// Optionally scale the icon
		Image img = icon.getImage(); // Get the image
		Image scaledImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Scale it
		icon = new ImageIcon(scaledImg); // Set the scaled icon

		mnuMaster.setIcon(icon);

	}

	public void loadMenuItem(JMenuItem mnuMaster) {
		// TODO Auto-generated method stub
		ImageIcon icon = new ImageIcon("D:\\Gokul\\GBilling\\src\\main\\resources\\resource\\Mark.png");

		// Optionally scale the icon
		Image img = icon.getImage(); // Get the image
		Image scaledImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Scale it
		icon = new ImageIcon(scaledImg); // Set the scaled icon

		mnuMaster.setIcon(icon);

	}

}
