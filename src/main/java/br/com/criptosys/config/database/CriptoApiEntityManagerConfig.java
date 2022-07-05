package br.com.criptosys.config.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class CriptoApiEntityManagerConfig {

    @Value("${criptosys.datasource.driver-class-name}")
    private String driverDatabase;

    @Value("${criptosys.datasource.url}")
    private String urlDatabase;

    @Value("${criptosys.datasource.username}")
    private String userNameDatabase;

    @Value("${criptosys.datasource.password}")
    private String passwordDatabase;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean(name = "dataSourceCriptoApi")
    @Primary
    public DataSource criptoApiDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverDatabase);
        dataSource.setUrl(urlDatabase);
        dataSource.setUsername(userNameDatabase);
        dataSource.setPassword(passwordDatabase);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(criptoApiDataSource());
        em.setPackagesToScan("br.com.criptosys.domain.entity");
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", strategy);
        properties.setProperty("hibernate.current_session_context_class"
                , "org.springframework.orm.hibernate5.SpringSessionContext");
        return properties;
    }

}
