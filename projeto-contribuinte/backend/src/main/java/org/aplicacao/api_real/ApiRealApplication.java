package org.aplicacao.api_real;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApiRealApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiRealApplication.class, args);
    }

}
