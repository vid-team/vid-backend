package com.vid.vidbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class VidBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(VidBackendApplication.class, args);
    }

}
