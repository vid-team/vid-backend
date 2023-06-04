package com.vid.vidbackend.global.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
@Profile("local")
@Configuration
public class H2ServerConfig {

    private static final int H2PORT = 9092;

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource dataSource() throws SQLException {
        Server server = defaultRun(H2PORT);
        if (server.isRunning(true)) {
            log.info("H2 Server run success");
        }

        log.info("h2 server url = {}", server.getURL());
        return new HikariDataSource();
    }

    private Server defaultRun(int port) throws SQLException {
        return Server.createTcpServer(
                "-tcp",
                "-tcpAllowOthers",
                "-ifNotExists",
                "-tcpPort", String.valueOf(port)).start();
    }
}
