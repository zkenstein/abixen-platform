/**
 * Copyright (c) 2010-present Abixen Systems. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.abixen.platform.common.infrastructure.configuration;

import com.abixen.platform.common.infrastructure.configuration.properties.AbstractPlatformJdbcConfigurationProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public abstract class AbstractPlatformDataSourceConfiguration {
    @Autowired
    AbstractPlatformJdbcConfigurationProperties platformJdbcConfiguration;

    @Bean(destroyMethod = "close")
    @LiquibaseDataSource
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(platformJdbcConfiguration.getDatabaseUrl());
        config.setUsername(platformJdbcConfiguration.getUsername());
        config.setPassword(platformJdbcConfiguration.getPassword());
        config.setDriverClassName(platformJdbcConfiguration.getDriverClassName());
        //config.setConnectionTestQuery("SELECT 1");
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }
}
