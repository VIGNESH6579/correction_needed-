package com.interview.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.interview.portal.repository")
@EntityScan(basePackages = "com.interview.portal.model")
public class InterviewPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewPortalApplication.class, args);
    }
}
