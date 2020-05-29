package com.peak.eshop.price;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EshopPriceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopPriceApplication.class, args);
    }
}
