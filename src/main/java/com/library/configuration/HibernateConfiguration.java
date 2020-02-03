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
@EnableJpaRepositories("com.library.dao")
@ComponentScan("com.library")
public class HibernateConfiguration {

    private final static String DATABASE_DIALECT = "org.hibernate.dialect.PostgreSQL10Dialect";
    private final static String DATABASE_HBM2DDL_AUTO = "update";
    private final static String DATABASE_SHOW_SQL = "true";

    private final static String DATABASE_DRIVER = "org.postgresql.Driver";
    private final static String DATABASE_URL = "jdbc:postgresql://localhost:5433/library";
    private final static String DATABASE_USERNAME = "postgres";
    private final static String DATABASE_PASSWORD = "123";

    private final static String PACKAGES_MODEL = "com.library.model";

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        factory.setDataSource(dataSource);
        factory.setJpaVendorAdapter(jpaVendorAdapter);
        factory.setJpaProperties(hibernateProperties());
        factory.setPackagesToScan(PACKAGES_MODEL);

        return factory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driver = new DriverManagerDataSource();

        driver.setDriverClassName(DATABASE_DRIVER);
        driver.setUrl(DATABASE_URL);
        driver.setUsername(DATABASE_USERNAME);
        driver.setPassword(DATABASE_PASSWORD);

        return driver;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter
                = new HibernateJpaVendorAdapter();

        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
       // hibernateJpaVendorAdapter.setGenerateDdl(true);

        return hibernateJpaVendorAdapter;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();

        hibernateProperties.setProperty(
                Environment.HBM2DDL_AUTO, DATABASE_HBM2DDL_AUTO);
        hibernateProperties.setProperty(
                Environment.DIALECT, DATABASE_DIALECT);
        hibernateProperties.setProperty(
                Environment.SHOW_SQL, DATABASE_SHOW_SQL);

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