package com.dislicores.api.b2c.app.product.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "db1EntityManagerFactory",
        basePackages = {"com.dislicores.api.b2c.app.product.repository.middle"},
        transactionManagerRef = "db1TransactionManager"
)
public class MiddleDBConfig {
    @Primary
    @Bean(name = "db1DataSourceProperties")
    @ConfigurationProperties("spring.datasource.middle")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "db1DataSource")
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource dataSource(@Qualifier("db1DataSourceProperties") DataSourceProperties db1DataSourceProperties) {
        return db1DataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "db1Properties")
    @ConfigurationProperties("spring.jpa.properties.hibernate")
    public Properties properties() {
        return new Properties();
    }

    @Primary
    @Bean(name = "db1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("db1DataSource") DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean factory = builder
                .dataSource(dataSource)
                .packages("com.dislicores.api.b2c.app.product.model.middle")
                .persistenceUnit("db1")
                .build();
        factory.setJpaProperties(this.properties());
        return factory;
    }

    @Primary
    @Bean(name = "db1TransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("db1EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
