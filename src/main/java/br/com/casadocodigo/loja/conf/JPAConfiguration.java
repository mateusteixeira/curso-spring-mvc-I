package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

		containerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUsername("admin");
		driverManagerDataSource.setPassword("admin");
		driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/casadocodigo");
		driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
		
		containerEntityManagerFactoryBean.setDataSource(driverManagerDataSource);
		
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
		props.setProperty("hibernate.show_sql", "true");
//		props.setProperty("hibernate.hbm2ddl.auto", "update");
		
		containerEntityManagerFactoryBean.setJpaProperties(props);
		containerEntityManagerFactoryBean.setPackagesToScan("br.com.casadocodigo.loja.models");
		
		return containerEntityManagerFactoryBean;
	}

	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
