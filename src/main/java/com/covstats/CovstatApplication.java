package com.covstats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
public class CovstatApplication {
    public static void main(String[] args) {
        SpringApplication.run(CovstatApplication.class, args);
    }
}
