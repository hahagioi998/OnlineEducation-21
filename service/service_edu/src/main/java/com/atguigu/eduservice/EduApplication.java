package com.atguigu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName EduApplication
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-11 22:58
 * @Version 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients//服务调用
@ComponentScan(basePackages = "com.atguigu")
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }

}
