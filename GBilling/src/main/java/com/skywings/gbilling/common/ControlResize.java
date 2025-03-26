package com.skywings.gbilling.common;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ControlResize {
	private double screenWidth = 0.0D;
	private double screenHeight = 0.0D;
	private JFrame jFrame;
	private JInternalFrame jInternalFrame;
	private JDialog jDialog;
	private Rectangle rectangle;
	private double minimumWidth = 0.0D;
	private double minimumHeight = 0.0D;
	private Dimension dimension;

	public ControlResize(JDialog jDialog) {
		this.jDialog = jDialog;
		GraphicsConfiguration oConfig = this.jDialog.getGraphicsConfiguration();
		DesignEnum.OperatingSystemType operatingSystemType = SystemProperties.getOsType();
		if (operatingSystemType == DesignEnum.OperatingSystemType.LINUX) {
			this.rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		} else {
			this.rectangle = oConfig.getBounds();
		}

		this.minimumWidth = (double) this.jDialog.getSize().width;
		this.minimumHeight = (double) this.jDialog.getSize().height;
		this.screenWidth = (double) this.rectangle.width;
		this.screenHeight = (double) this.rectangle.height;
	}

	public ControlResize(JFrame jFrame) {
		this.jFrame = jFrame;
		GraphicsConfiguration oConfig = this.jFrame.getGraphicsConfiguration();
		DesignEnum.OperatingSystemType operatingSystemType = SystemProperties.getOsType();
		if (operatingSystemType == DesignEnum.OperatingSystemType.LINUX) {
			this.rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		} else {
			this.rectangle = oConfig.getBounds();
		}

		this.minimumWidth = (double) this.jFrame.getSize().width;
		this.minimumHeight = (double) this.jFrame.getSize().height;
		this.screenWidth = (double) this.rectangle.width;
		this.screenHeight = (double) this.rectangle.height;
	}

	public ControlResize(JInternalFrame jInternalFrame, Dimension dimension) {
		this.jInternalFrame = jInternalFrame;
		this.dimension = dimension;
		GraphicsConfiguration oConfig = this.jInternalFrame.getGraphicsConfiguration();
		DesignEnum.OperatingSystemType operatingSystemType = SystemProperties.getOsType();
		if (operatingSystemType == DesignEnum.OperatingSystemType.LINUX) {
			this.rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		} else if (oConfig != null) {
			this.rectangle = oConfig.getBounds();
		}

		this.minimumWidth = 958.0D;
		this.minimumHeight = 528.0D;
		this.screenWidth = (double) this.dimension.width;
		this.screenHeight = (double) this.dimension.height;
	}

	public Rectangle getRectangle() {
		return this.rectangle;
	}

	public void reAlignControls() throws Exception {
		this.parentJFrameControl();
	}

	private void parentJFrameControl() throws Exception {
		try {
			Container jFrameContainer = null;
			if (this.jFrame != null || this.jInternalFrame != null || null != this.jDialog) {
				if (this.jFrame != null) {
					jFrameContainer = this.jFrame.getContentPane();
				}

				if (this.jInternalFrame != null) {
					jFrameContainer = this.jInternalFrame.getContentPane();
				}

				if (this.jDialog != null) {
					jFrameContainer = this.jDialog.getContentPane();
				}

				int compCount = jFrameContainer.getComponentCount();

				for (int i = 0; i < compCount; ++i) {
					Component component = jFrameContainer.getComponent(i);
					if (!(component instanceof JPanel) && !(component instanceof JTabbedPane)) {
						this.parentControlAlign(component);
					} else {
						this.parentControlAlign(component);
						Container cContainer = (Container) component;
						this.resizeContainers(cContainer);
					}
				}

			}
		} catch (Exception var6) {
			var6.printStackTrace();
			throw var6;
		}
	}

	private void resizeContainers(Container container) throws Exception {
		try {
			int compCount = container.getComponentCount();

			for (int i = 0; i < compCount; ++i) {
				Component component = container.getComponent(i);
				if (!(component instanceof JPanel) && !(component instanceof JTabbedPane)) {
					this.parentControlAlign(component);
				} else {
					this.parentControlAlign(component);
					Container cContainer = (Container) component;
					this.resizeContainers(cContainer);
				}
			}

		} catch (Exception var6) {
			throw var6;
		}
	}

	private void parentControlAlign(Component jParentComponent) throws Exception {
		if (jParentComponent.getComponentOrientation() == ComponentOrientation.UNKNOWN) {
			jParentComponent.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			double controlWidth = 0.0D;
			double controlHeight = 0.0D;
			double controlX = 0.0D;
			double controlY = 0.0D;
			double controlWidthPercentage = 0.0D;
			double controlHeightPercentage = 0.0D;
			double controlXPercentage = 0.0D;
			double controlYPercentage = 0.0D;

			try {
				double X = (double) ((int) jParentComponent.getLocation().getX());
				double Y = (double) ((int) jParentComponent.getLocation().getY());
				double Width = (double) ((int) jParentComponent.getSize().getWidth());
				double Height = (double) ((int) jParentComponent.getSize().getHeight());
				controlXPercentage = X * 100.0D / this.minimumWidth;
				controlYPercentage = Y * 100.0D / this.minimumHeight;
				controlWidthPercentage = Width * 100.0D / this.minimumWidth;
				controlHeightPercentage = Height * 100.0D / this.minimumHeight;
				controlWidth = this.screenWidth / 100.0D * controlWidthPercentage;
				controlHeight = this.screenHeight / 100.0D * controlHeightPercentage;
				controlX = this.screenWidth / 100.0D * controlXPercentage;
				controlY = this.screenHeight / 100.0D * controlYPercentage;
				jParentComponent.setBounds((int) controlX, (int) controlY, (int) controlWidth, (int) controlHeight);
				jParentComponent.repaint();
			} catch (Exception var26) {
				throw var26;
			}
		}

	}
}
