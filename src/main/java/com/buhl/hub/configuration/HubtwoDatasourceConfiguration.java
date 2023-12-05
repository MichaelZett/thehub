package com.buhl.hub.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJdbcRepositories(jdbcOperationsRef = "hubtwoJdbcOperations",
        transactionManagerRef = "hubtwoTransactionManager",
        basePackages = "com.buhl.hub.customer.domain")
public class HubtwoDatasourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.hubtwo")
    public DataSourceProperties hubtwoDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource hubtwoDataSource() {
        return hubtwoDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    PlatformTransactionManager hubtwoTransactionManager(@Qualifier("hubtwoDataSource") DataSource hubtwoDataSource) {
        return new JdbcTransactionManager(hubtwoDataSource);
    }

    @Bean
    public NamedParameterJdbcOperations hubtwoJdbcOperations(@Qualifier("hubtwoDataSource") DataSource hubtwoDataSource) {
        return new NamedParameterJdbcTemplate(hubtwoDataSource);
    }

    @Bean
    public JdbcTemplate hubtwoJdbcTemplate(@Qualifier("hubtwoDataSource") DataSource hubtwoDataSource) {
        return new JdbcTemplate(hubtwoDataSource);
    }
}