package com.ohh.icloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.ohh.icloud.mapper")
public class ICloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(ICloudApplication.class, args);
    }

}
