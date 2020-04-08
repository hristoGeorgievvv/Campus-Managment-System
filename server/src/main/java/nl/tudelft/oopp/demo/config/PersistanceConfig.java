package nl.tudelft.oopp.demo.config;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * This is like a better application.properties file. Here we define our
 * EntityManagerFactory with the proper configuration.
 */
@Configuration
@EnableTransactionManagement
public class PersistanceConfig {

    // Due to a weird bug with hibernate this has to be defined

    /**
     * Initialises the entity manager factory bean.
     * @return returns the specified entity manager factory bean
     */
    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        // Important as this is where we tell hibernate where to look for our entities.
        em.setPackagesToScan("nl.tudelft.oopp.demo.entities");
        em.setJpaVendorAdapter(vendorAdapter);
        em.setDataSource(dataSource());
        em.setJpaProperties(additionalProperties());

        List<Integer> cur = new LinkedList<Integer>();


        return em;
    }

    /**
     * Sets the data source to our specified database.
     * @return our data source: the database
     */
    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(
                "");
        dataSource.setUsername("");
        dataSource.setPassword("");

        return dataSource;
    }

    /**
     * Creates the transactionManager beans.
     * @param emf The incoming entity manager factory for the transaction manager
     * @return the transaction manager with the specified entity manager factory
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");
        return properties;
    }
}
