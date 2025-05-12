package com.vitorbionic.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.vitorbionic.repository.objectdb",
    entityManagerFactoryRef = "objectdbEntityManager",
    transactionManagerRef = "objectdbTransactionManager"
)
public class ObjectDbConfig {

    @Bean
    LocalContainerEntityManagerFactoryBean objectdbEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPersistenceUnitName("objectdbPU");
        em.setPackagesToScan("com.vitorbionic.model.objectdb");

        Map<String, Object> properties = new HashMap<>();
        properties.put("jakarta.persistence.jdbc.url", "objectdb://localhost:6136/mydb.odb");
        properties.put("jakarta.persistence.jdbc.user", "admin");
        properties.put("jakarta.persistence.jdbc.password", "admin");

        em.setJpaPropertyMap(properties);
        em.setPersistenceProviderClass(com.objectdb.jpa.Provider.class);
        return em;
    }

    @Bean
    PlatformTransactionManager objectdbTransactionManager(
            @Qualifier("objectdbEntityManager") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
