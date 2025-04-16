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
        entityManagerFactoryRef = "db2EntityManagerFactory",
        basePackages = {"com.dislicores.api.b2c.app.product.repository.erp"},
        transactionManagerRef = "db2TransactionManager"
)
public class ErpDBConfig {
    @Bean(name = "db2DataSourceProperties")
    @ConfigurationProperties("spring.datasource.erp")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "db2DataSource")
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource dataSource(@Qualifier("db2DataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "db2Properties")
    @ConfigurationProperties("spring.jpa.properties.hibernate")
    public Properties properties() {
        return new Properties();
    }

    @Bean(name = "db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("db2DataSource") DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean factory = builder
                .dataSource(dataSource)
                .packages("com.dislicores.api.b2c.app.product.model.erp")
                .persistenceUnit("db2")
                .build();
        factory.setJpaProperties(this.properties());
        return factory;
    }

    @Bean(name = "db2TransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("db2EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
