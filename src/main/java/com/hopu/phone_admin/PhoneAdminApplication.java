package com.hopu.phone_admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hopu.phone_admin.dao")
public class PhoneAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneAdminApplication.class, args);
    }

}
