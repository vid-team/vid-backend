package com.vid.vidbackend;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;

@SpringBootApplication
public class VidBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(VidBackendApplication.class, args);
    }

}
