package com.skywings.gbilling.start;

import java.security.Security;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.skywings.gbilling.common.CustomFonts;
import com.skywings.gbilling.config.ApplicationConfig;
import com.skywings.gbilling.form.FrmLogin;

public class ApplicationStart {

	public static void main(String[] args) {

		try {

			String disabledAlgorithms = Security.getProperty("jdk.tls.disabledAlgorithms");
			String[] algorithms = disabledAlgorithms.split(",");
			for (int i = 0; i < algorithms.length; i++) {
				String string = algorithms[i];
				if ("TLSv1".contains(string.trim()) || "TLSv1.1".contains(string.trim())) {
					algorithms[i] = null;
				}
			}
			String altered = "";
			for (int i = 0; i < algorithms.length; i++) {
				String string = algorithms[i];
				if (null == string) {
					continue;
				}

				altered += string + ",";
			}
			Security.setProperty("jdk.tls.disabledAlgorithms", altered);

			// to merge button focus and cursor foucs
			UIManager.put("Button.defaultButtonFollowsFocus", true);

			// to set custom message font
			UIManager.put("OptionPane.messageFont", CustomFonts.font20);

			AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(
					ApplicationConfig.class);
			Applicationmain.setAbstractApplicationContext(applicationContext);

//			
//
//			FrmSalesEntry frmLogin = Applicationmain.getAbstractApplicationContext().getBean(FrmSalesEntry.class);
//			frmLogin.setVisible(true);

			FrmLogin frmLogin = Applicationmain.getAbstractApplicationContext().getBean(FrmLogin.class);
			frmLogin.setVisible(true);

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();

		}

	}

}
