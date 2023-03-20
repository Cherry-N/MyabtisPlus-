package com.longxingyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Cyan-
 */
@SpringBootApplication
@MapperScan("com.longxingyu.mapper")
public class MyabtisPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyabtisPlusApplication.class, args);
    }
}
