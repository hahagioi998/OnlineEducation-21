package com.atguigu.eduorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName OrdersApplication
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-27 14:21
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.atguigu")
@MapperScan("com.atguigu.eduorder.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class OrdersApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class,args);
    }
}
