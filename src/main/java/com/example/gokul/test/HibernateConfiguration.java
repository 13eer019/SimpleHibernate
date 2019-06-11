package com.example.gokul.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {


    /*@Autowired
    public LocalSessionFactoryBean localSessionFactoryBean*/;


    @Value("${db.driver}")
    private String DB_DRIVER;

    @Value("${db.password}")
    private String DB_PASSWORD;

    @Value("${db.url}")
    private String DB_URL;

    @Value("${db.username}")
    private String DB_USERNAME;

    @Value("${hibernate.dialect}")
    private String HIBERNATE_DIALECT;

    @Value("${hibernate.show_sql}")
    private String HIBERNATE_SHOW_SQL;

    @Value("${hibernate.hbm2ddl.auto}")
    private String HIBERNATE_HBM2DDL_AUTO;

    @Value("${entitymanager.packagesToScan}")
    private String ENTITYMANAGER_PACKAGES_TO_SCAN;

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(){
        System.out.println("===========================================================");
        System.out.println(DB_DRIVER);
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        System.out.println(DB_URL);
        sessionFactoryBean.setDataSource(dataSource());

        System.out.println("===========================================================1");
        sessionFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
        Properties properties = new Properties();
        properties.put("hibernate.dialect",HIBERNATE_DIALECT);
        properties.put("hibernate.hbm2ddl.auto",HIBERNATE_HBM2DDL_AUTO);
        properties.put("hibernate.show_sql",HIBERNATE_SHOW_SQL);

        System.out.println("===========================================================2");
        sessionFactoryBean.setHibernateProperties(properties);
         return sessionFactoryBean;
    }

    @Bean
    public DataSource dataSource() {
        System.out.println("===========================================================3");
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(DB_DRIVER);
        driverManagerDataSource.setUrl(DB_URL);
        driverManagerDataSource.setUsername(DB_USERNAME);
        driverManagerDataSource.setPassword(DB_PASSWORD);
        System.out.println("===========================================================4");
        return driverManagerDataSource;
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactoryBean().getObject());
        return transactionManager;
    }
}


