//package com.hrms.config.db;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "entityManagerFactoryBpmsDb",
//        transactionManagerRef = "transactionManagerBpmsDb",
//        basePackages = {
//                "com.hrms.bpms.entity",
//                "com.hrms.bpms.repository"
//        }
//)
//public class BpmsDBConfig {
//
//    @Bean(name = "dataSourceBpmsDb")
//    @ConfigurationProperties("spring.bpmsdb")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "jdbcBpmsDb")
//    public JdbcTemplate mainJdbcTemplate(@Qualifier("dataSourceBpmsDb") DataSource dsMaster) {
//        return new JdbcTemplate(dsMaster);
//    }
//
//    @Bean(name = "entityManagerFactoryBuilderBpmsDb")
//    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
//        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap(), null);
//    }
//
//
//    @Bean(name = "entityManagerFactoryBpmsDb")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            @Qualifier("entityManagerFactoryBuilderBpmsDb") EntityManagerFactoryBuilder builder,
//            @Qualifier("dataSourceBpmsDb") DataSource dataSource
//    ) {
//
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", "none");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//
//        return builder
//                .dataSource(dataSource)
//                .packages("com.hrms.bpms.entity")
//                .persistenceUnit("bpms")
//                .properties(properties)
//                .build();
//
//    }
//
//    @Bean(name = "transactionManagerBpmsDb")
//    public PlatformTransactionManager transactionManager(
//            @Qualifier("entityManagerFactoryBpmsDb") EntityManagerFactory absEntityManagerFactory) {
//        return new JpaTransactionManager(absEntityManagerFactory);
//    }
//}
