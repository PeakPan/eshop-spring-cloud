package com.peak.eshop.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EshopInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopInventoryApplication.class, args);
    }
}
