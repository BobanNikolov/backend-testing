package com.backend.testing.application.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.backend.testing")
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
