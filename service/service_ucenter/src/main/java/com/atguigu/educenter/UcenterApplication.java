package com.atguigu.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName UcenterApplication
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-25 15:31
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan("com.atguigu")
@MapperScan(basePackages = "com.atguigu.educenter.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
