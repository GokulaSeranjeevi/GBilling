package com.skywings.gbilling.start;

import javax.swing.JOptionPane;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.skywings.gbilling.common.FormAction;
import com.skywings.gbilling.form.FrmLogin;

public class Applicationmain {

	private static Applicationmain app = new Applicationmain();
	public static final String applicationName = "GBilling";
	private static JdbcTemplate tranJdbcTemplate;
	private static AbstractApplicationContext abstractApplicationContext;
	private static final int MAJOR = 25;
	private static final int MINOR = 01;
	private static final int REVISION = 22;
	private static final int BUILD = 1;
	public static final String VERSION = BUILD + "." + MAJOR + "." + MINOR + "." + REVISION;
	public static final boolean JTDS = false;
	public static int internalFrameWidth = 1300;
	public static int internalFrameheight = 800;
	public static FormAction currentForm;

	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public final String connAddlSettings = "?allowPublicKeyRetrieval=true&useSSL=false&autoReconnect=true&connectionAttributes=program_name:"
			+ applicationName;

	public static String masterDbName = "MKSGMaster";
	public static String tranName = "MKST2425";

	public static int getInternalFrameWidth() {
		return internalFrameWidth;
	}

	public static void setInternalFrameWidth(int internalFrameWidth) {
		Applicationmain.internalFrameWidth = internalFrameWidth;
	}

	public static int getInternalFrameheight() {
		return internalFrameheight;
	}

	public static void setInternalFrameheight(int internalFrameheight) {
		Applicationmain.internalFrameheight = internalFrameheight;
	}

	public static FrmLogin frmLogin;

	public static FrmLogin getFrmLogin() {
		return frmLogin;
	}

	public static void setFrmLogin(FrmLogin frmLogin) {
		Applicationmain.frmLogin = frmLogin;
	}

	private String url = "";

	public static AbstractApplicationContext getAbstractApplicationContext() {
		return abstractApplicationContext;
	}

	public static void setAbstractApplicationContext(AbstractApplicationContext abstractApplicationContext) {
		Applicationmain.abstractApplicationContext = abstractApplicationContext;
	}

	public static Applicationmain getApp() {
		return app;
	}

	public static JdbcTemplate getTranDBJdbcTemplate() {
		return tranJdbcTemplate;
	}

	public static void setTranDBJdbcTemplate(JdbcTemplate tranJdbcTemplate) {
		Applicationmain.tranJdbcTemplate = tranJdbcTemplate;
	}

	public static void setApp(Applicationmain app) {
		Applicationmain.app = app;
	}

}
