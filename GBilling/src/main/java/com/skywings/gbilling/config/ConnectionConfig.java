package com.skywings.gbilling.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.skywings.gbilling.common.CommonMethods;
import com.skywings.gbilling.common.PasswordValidation;
import com.skywings.gbilling.start.Applicationmain;

public class ConnectionConfig {

	private Applicationmain app = Applicationmain.getApp();

	private DataSource getDataSource(String dbName, String url) throws Exception {

		DriverManagerDataSource driverManagerDataSource = null;

		driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(CommonMethods.getDriverName());

		driverManagerDataSource.setUrl(url);
		driverManagerDataSource.setUsername(CommonMethods.strLogin);
		driverManagerDataSource.setPassword(PasswordValidation.decript(CommonMethods.strPassword));
		return driverManagerDataSource;

	}

	@Bean(name = "MasterDb")
	private JdbcTemplate getJdbcTemplate() throws Exception {
		String url = CommonMethods.getUrl(Applicationmain.masterDbName);
		return new JdbcTemplate(getDataSource(Applicationmain.masterDbName, url));

	}

	@Bean(name = "tranJdbcTemplate")
	private JdbcTemplate getJdbcTemplate1() throws Exception {
		String url = CommonMethods.getUrl(Applicationmain.tranName);
		return new JdbcTemplate(getDataSource(Applicationmain.tranName, url));
	}
}
