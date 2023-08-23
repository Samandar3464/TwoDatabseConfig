package uz.xb.projectwithtwodb.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "entityManagerFactoryPostgres",
        transactionManagerRef = "transactionManagerPostgres",
        basePackages = {
//                "uz.xb.projectwithtwodb.entity.postgres",
                "uz.xb.projectwithtwodb.repository.postgres"
        }
)
@RequiredArgsConstructor
public class PostgresConfig {

    private final Environment e;

    @Primary
    @Bean(name = "postgresDataSource")
    @ConfigurationProperties(prefix = "spring.postgres")
    public DataSource dataSource() {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setUrl(e.getProperty("spring.postgres.url"));
//        ds.setUsername(e.getProperty("spring.postgres.username"));
//        ds.setPassword(e.getProperty("spring.postgres.password"));
//        ds.setDriverClassName(e.getProperty("spring.postgres.driver-class-name"));
//        return ds;
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "postgres")
    public JdbcTemplate mainJdbcTemplate(@Qualifier("postgresDataSource") DataSource dsMaster) {
        return new JdbcTemplate(dsMaster);
    }

//    @Primary
//    @Bean(name = "entityManagerFactoryBuilder")
//    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
//        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap(), null);
//    }
//
//    @Primary
//    @Bean(name = "entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            @Qualifier("entityManagerFactoryBuilder") EntityManagerFactoryBuilder builder,
//            @Qualifier("dataSource") DataSource dataSource
//    ) {
//
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", "none");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//
//        return builder
//                .dataSource(dataSource)
//                .packages("com.hrms.entity")
//                .properties(properties)
//                .build();
//
//    }

    @Primary
    @Bean(name = "entityManagerFactoryPostgres")
    public LocalContainerEntityManagerFactoryBean entityManager() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(adapter);
        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        bean.setJpaPropertyMap(properties);
        bean.setPackagesToScan("uz.xb.projectwithtwodb.entity.postgres");
        return bean;
    }
    @Primary
    @Bean(name = "transactionManagerPostgres")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactoryPostgres") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}