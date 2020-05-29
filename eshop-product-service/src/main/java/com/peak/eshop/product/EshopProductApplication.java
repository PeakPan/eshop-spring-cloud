package com.peak.eshop.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EshopProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopProductApplication.class, args);
    }
}
