package com.peak.eshop.datalink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 数据直连服务
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients
public class EshopDatalinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopDatalinkApplication.class, args);
    }
}
