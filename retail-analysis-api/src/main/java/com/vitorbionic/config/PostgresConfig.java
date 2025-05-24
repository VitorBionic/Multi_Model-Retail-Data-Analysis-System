package com.vitorbionic.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.vitorbionic.repository.postgres",
    entityManagerFactoryRef = "postgresEntityManager",
    transactionManagerRef = "postgresTransactionManager"
)
public class PostgresConfig {

    @Bean
    DataSource postgresDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5433/retail_analysis")
                .username("postgres")
                .password("root")
                .driverClassName("org.postgresql.Driver")
                .build();
    }

    
    @Bean
    @Primary
    LocalContainerEntityManagerFactoryBean postgresEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(postgresDataSource());
        em.setPackagesToScan("com.vitorbionic.model.postgres");
        em.setPersistenceUnitName("postgresPU");
        
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "update");

        em.setJpaPropertyMap(props);
        return em;
    }

    @Bean
    @Primary
    PlatformTransactionManager postgresTransactionManager(
            @Qualifier("postgresEntityManager") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
