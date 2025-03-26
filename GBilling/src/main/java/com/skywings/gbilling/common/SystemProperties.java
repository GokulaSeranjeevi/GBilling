package com.skywings.gbilling.common;

public class SystemProperties {
	private SystemProperties() {
	}

	public static DesignEnum.OperatingSystemType getOsType() {
		DesignEnum.OperatingSystemType operatingSystemType;
		if (System.getProperties().getProperty("os.name") != null
				&& System.getProperties().getProperty("os.name").toLowerCase().contains("windows")) {
			operatingSystemType = DesignEnum.OperatingSystemType.WINDOWS;
		} else {
			operatingSystemType = DesignEnum.OperatingSystemType.LINUX;
		}

		return operatingSystemType;
	}

	public static DesignEnum.DesktopType getDesktopType() {
		DesignEnum.DesktopType desktopType = DesignEnum.DesktopType.GNOME;
		if (System.getProperties().getProperty("sun.desktop") == null) {
			desktopType = DesignEnum.DesktopType.NONE;
		} else if (System.getProperties().getProperty("sun.desktop").toLowerCase().contains("gnome")) {
			desktopType = DesignEnum.DesktopType.GNOME;
		} else if (System.getProperties().getProperty("sun.desktop").toLowerCase().contains("windows")) {
			desktopType = DesignEnum.DesktopType.WINDOWS;
		}

		return desktopType;
	}
}
