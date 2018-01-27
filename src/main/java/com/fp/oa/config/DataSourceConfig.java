package com.fp.oa.config;

import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@EnableJpaRepositories(entityManagerFactoryRef = "orderEntityManagerFactory",
basePackages ="com.fp.oa")
@Configuration
public class DataSourceConfig {
	private final static Logger LOG = LoggerFactory.getLogger(DataSourceConfig.class);

	@Value("${local.h2.url:jdbc:h2:~/.h2/testdb}")
	private String localH2Url;

	@Value("${local.h2.username:sa}")
	private String localH2Username;

	@Value("${local.h2.password:}")
	private String localH2Password;
	
	@Autowired
	Environment env;


	@Bean(name = "standalone")
	//@DependsOn("h2WebServer")
	public DataSource h2Standalone() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl(this.localH2Url);
		ds.setUsername(this.localH2Username);
		ds.setPassword(this.localH2Password);
		return ds;
	}
	
	@Bean
	LocalContainerEntityManagerFactoryBean orderEntityManagerFactory(DataSource dataSource) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(true);
		vendorAdapter.setGenerateDdl(false);
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		factoryBean.setPackagesToScan("com.fp.oa");
		return factoryBean;
	}

	
	@Bean(name = "h2WebServer", destroyMethod = "stop")
	public Server h2WebServer() throws SQLException {
		LOG.info("--- create h2 database service ---");
		return Server.createWebServer("-tcpPort", "8082", "-trace").start();
	}
}
