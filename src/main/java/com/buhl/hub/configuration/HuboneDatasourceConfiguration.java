package com.buhl.hub.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
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
@EnableJdbcRepositories(jdbcOperationsRef = "huboneJdbcOperations",
        transactionManagerRef = "huboneTransactionManager",
        basePackages = "com.buhl.hub.shop.domain")
public class HuboneDatasourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.hubone")
    public DataSourceProperties huboneDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource huboneDataSource() {
        return huboneDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    PlatformTransactionManager huboneTransactionManager(@Qualifier("huboneDataSource") DataSource huboneDataSource) {
        return new JdbcTransactionManager(huboneDataSource);
    }

    @Bean
    public NamedParameterJdbcOperations huboneJdbcOperations(@Qualifier("huboneDataSource") DataSource huboneDataSource) {
        return new NamedParameterJdbcTemplate(huboneDataSource);
    }

    @Bean
    public JdbcTemplate huboneJdbcTemplate(@Qualifier("huboneDataSource") DataSource huboneDataSource) {
        return new JdbcTemplate(huboneDataSource);
    }
}