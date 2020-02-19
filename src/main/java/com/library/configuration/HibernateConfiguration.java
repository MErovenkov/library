package com.library.configuration;

import org.hibernate.cfg.Environment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@ComponentScan("com.library.dao")
@ComponentScan("com.library.configuration")
public class HibernateConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        factory.setDataSource(dataSource);
        factory.setJpaVendorAdapter(jpaVendorAdapter);
        factory.setJpaProperties(hibernateProperties());
        factory.setPackagesToScan("com.library.model");

        return factory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driver = new DriverManagerDataSource();

        driver.setDriverClassName("org.postgresql.Driver");
        driver.setUrl("jdbc:postgresql://localhost:5433/library");
        driver.setUsername("postgres");
        driver.setPassword("123");

        return driver;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter
                = new HibernateJpaVendorAdapter();

        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);

        return hibernateJpaVendorAdapter;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();

        hibernateProperties.setProperty(
                Environment.HBM2DDL_AUTO, "update");
        hibernateProperties.setProperty(
                Environment.DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");
        hibernateProperties.setProperty(
                Environment.SHOW_SQL, "true");

        return hibernateProperties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource, EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

}