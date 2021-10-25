package com.atguigu.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName CmsApplication
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-24 12:21
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan({"com.atguigu"}) //指定扫描位置
@MapperScan("com.atguigu.educms.mapper")
@EnableFeignClients
@EnableDiscoveryClient
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
