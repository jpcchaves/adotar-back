package com.jpcchaves.adotar.infrastructure.config.swagger;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SecurityScheme(
    name = "Bearer Authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer")
@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Adote.ME - Rest API")
                .version("v1")
                .contact(
                    new Contact()
                        .url("https://www.linkedin.com/in/joaopaulo-chaves/")
                        .email("jpcchaves@outlook.com"))
                .description(
                    "REST API built to manage a pet adoption application made with Spring Boot")
                .termsOfService("https://porfolio-jpcchaves.vercel.app/")
                .license(
                    new License()
                        .name("MIT")
                        .url("https://porfolio-jpcchaves.vercel.app/")));
  }
}
