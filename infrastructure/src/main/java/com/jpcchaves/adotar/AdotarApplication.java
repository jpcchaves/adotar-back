package com.jpcchaves.adotar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AdotarApplication {

  public static void main(String[] args) {
    SpringApplication.run(AdotarApplication.class, args);
  }
}
