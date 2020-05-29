package com.peak.eshop.dataaggr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EshopDataaggrApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopDataaggrApplication.class, args);
    }
}
