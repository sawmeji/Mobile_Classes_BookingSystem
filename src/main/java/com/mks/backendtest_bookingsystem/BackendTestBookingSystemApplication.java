package com.mks.backendtest_bookingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.mks.backendtest_bookingsystem.repository")
@EntityScan("com.mks.backendtest_bookingsystem.entity")
public class BackendTestBookingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendTestBookingSystemApplication.class, args);
    }

}
