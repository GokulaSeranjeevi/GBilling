package com.skywings.gbilling.model;

import org.springframework.stereotype.Component;

@Component
public class MasterCommon {

	private String Description;
	private String Unit;
	private String HSNCode;
	private String HSNName;
	private String HSNActive;

	public String getHSNCode() {
		return HSNCode;
	}

	public void setHSNCode(String hSNCode) {
		HSNCode = hSNCode;
	}

	public String getHSNName() {
		return HSNName;
	}

	public void setHSNName(String hSNName) {
		HSNName = hSNName;
	}

	public String getHSNActive() {
		return HSNActive;
	}

	public void setHSNActive(String hSNActive) {
		HSNActive = hSNActive;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

}
