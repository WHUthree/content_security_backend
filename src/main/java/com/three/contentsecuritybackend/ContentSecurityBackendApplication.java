package com.three.contentsecuritybackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.three.contentsecuritybackend.mapper")
public class ContentSecurityBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentSecurityBackendApplication.class, args);
    }

}
