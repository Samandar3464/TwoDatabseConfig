package uz.xb.projectwithtwodb.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
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
        entityManagerFactoryRef = "entityManagerFactorySecond",
        transactionManagerRef = "transactionManagerSecond",
        basePackages = {
//                "uz.xb.projectwithtwodb.entity.second",
                "uz.xb.projectwithtwodb.repository.second"
        }
)
public class SecondDatabaseConfig {


    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = "spring.second")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcUser2")
    public JdbcTemplate mainJdbcTemplate(@Qualifier("secondDataSource") DataSource dsMaster) {
        return new JdbcTemplate(dsMaster);
    }

//    @Bean(name = "entityManagerFactoryBuilderSecond")
//    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
//        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap(), null);
//    }

//    @Bean(name = "entityManagerFactorySecond")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            @Qualifier("entityManagerFactoryBuilderSecond") EntityManagerFactoryBuilder builder,
//            @Qualifier("secondDataSource") DataSource dataSource
//    ) {
//
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", "none");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//
//        return builder
//                .dataSource(dataSource)
//                .packages("uz.xb.projectwithtwodb.entity.second")
//                .persistenceUnit("second")
//                .properties(properties)
//                .build();
//
//    }


    @Bean(name = "entityManagerFactorySecond")
    public LocalContainerEntityManagerFactoryBean entityManager() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(adapter);
        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        bean.setJpaPropertyMap(properties);
        bean.setPackagesToScan("uz.xb.projectwithtwodb.entity.second");
        return bean;
    }

    @Bean(name = "transactionManagerSecond")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactorySecond") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}