package com.devsblog1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DevsBlog1Application {

    public static void main(String[] args) {
        SpringApplication.run(DevsBlog1Application.class, args);
    }

}
