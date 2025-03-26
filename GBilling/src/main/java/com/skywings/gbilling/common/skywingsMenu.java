package com.skywings.gbilling.common;

import javax.swing.Action;
import javax.swing.JMenu;

public class skywingsMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	private int moduleId = 0;
	private int menuLevel = 0;
	private String password;
	private String formToLoad = "";
	private String moduleCode = "";
	private String menuCode = "";
	private JMenuEnum.FormType formType;

	public skywingsMenu() {
		this.formType = JMenuEnum.FormType.SAVE_VIEW_EDIT;
	}

	public skywingsMenu(Action arg0) {
		super(arg0);
		this.formType = JMenuEnum.FormType.SAVE_VIEW_EDIT;
	}

	public skywingsMenu(String arg0) {
		super(arg0);
		this.formType = JMenuEnum.FormType.SAVE_VIEW_EDIT;
	}

	public skywingsMenu(String arg0, boolean arg1) {
		super(arg0, arg1);
		this.formType = JMenuEnum.FormType.SAVE_VIEW_EDIT;
	}

	public int getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public int getMenuLevel() {
		return this.menuLevel;
	}

	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFormToLoad() {
		return this.formToLoad;
	}

	public void setFormToLoad(String formToLoad) {
		this.formToLoad = formToLoad;
	}

	public String getModuleCode() {
		return this.moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getMenuCode() {
		return this.menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public JMenuEnum.FormType getFormType() {
		return this.formType;
	}

	public void setFormType(JMenuEnum.FormType formType) {
		this.formType = formType;
	}

	public void setMenuDetails(int moduleId, int menuLevel, String menuCode, String moduleCode) {
		this.moduleId = moduleId;
		this.menuLevel = menuLevel;
		this.menuCode = menuCode;
		this.moduleCode = moduleCode;
	}
}
