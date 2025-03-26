package com.skywings.gbilling.common;

public class DesignEnum {
	public static enum DesktopType {
		WINDOWS, GNOME, NONE;

		// $FF: synthetic method
		private static DesignEnum.DesktopType[] $values() {
			return new DesignEnum.DesktopType[] { WINDOWS, GNOME, NONE };
		}
	}

	public static enum OperatingSystemType {
		WINDOWS, LINUX;

		// $FF: synthetic method
		private static DesignEnum.OperatingSystemType[] $values() {
			return new DesignEnum.OperatingSystemType[] { WINDOWS, LINUX };
		}
	}
}
