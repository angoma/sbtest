package org.agm.sbtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("org.agm.sbtest.entities")
@EnableJpaRepositories("org.agm.sbtest.repositories")
public class SbtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbtestApplication.class, args);
    }

}
