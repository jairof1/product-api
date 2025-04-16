package com.dislicores.api.b2c.app.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DislicoresB2cappProductApiApplication {
   public static void main(String[] args) {
      SpringApplication.run(DislicoresB2cappProductApiApplication.class, args);
   }
}