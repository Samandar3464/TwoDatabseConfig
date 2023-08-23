package uz.xb.projectwithtwodb.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mySqlEntityManagerFactory",
        transactionManagerRef = "transactionManagerMySql",
        basePackages = {
                "uz.xb.projectwithtwodb.entity.mysql",
                "uz.xb.projectwithtwodb.repository.mysql"}

)
@RequiredArgsConstructor
public class MySqlConfig {

    private final Environment e;

    @Bean(name = "mySqlDataSource")
//    @ConfigurationProperties(prefix = "spring.mysql")
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(e.getProperty("spring.mysql.url"));
        ds.setUsername(e.getProperty("spring.mysql.username"));
        ds.setPassword(e.getProperty("spring.mysql.password"));
        ds.setDriverClassName(e.getProperty("spring.mysql.driver-class-name"));
        return ds;
    }



    @Bean(name = "mySql")
    public JdbcTemplate mainJdbcTemplate(@Qualifier("mySqlDataSource") DataSource dsMaster) {
        return new JdbcTemplate(dsMaster);
    }



    @Bean(name = "mySqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManager() {
                LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(adapter);
        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        bean.setJpaPropertyMap(properties);
        bean.setPackagesToScan("uz.xb.projectwithtwodb.entity.mysql");
        return bean;

    }


    @Bean(name = "transactionManagerMySql")
    public PlatformTransactionManager transactionManager(@Qualifier("mySqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}