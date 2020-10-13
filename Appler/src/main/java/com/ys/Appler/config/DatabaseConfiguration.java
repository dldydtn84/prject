package com.ys.Appler.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Slf4j
@Configuration
@PropertySource("classpath:/application.yml")
public class DatabaseConfiguration {

    @Bean // dataSource()의 반환값 dataSource객체를 빈으로 등록하겠다.
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://database-1.cd5vpjxhrb6d.ap-northeast-2.rds.amazonaws.com:3306/appler?characterEncoding=utf8");
        hikariConfig.setUsername("administrator");
        hikariConfig.setPassword("ritkfygh12");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        return dataSource; // dataSource객체가 Bean으로 등록됨
    }

}