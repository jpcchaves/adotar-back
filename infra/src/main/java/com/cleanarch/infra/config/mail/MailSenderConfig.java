package com.cleanarch.infra.config.mail;

import java.util.Map;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailSenderConfig {

  @Value("${spring.mail.host}")
  private String host;

  @Value("${spring.mail.port}")
  private int port;

  @Value("${spring.mail.username}")
  private String username;

  @Value("${spring.mail.password}")
  private String password;

  private static final String UTF_8_CHARSET = "UTF-8";

  @Bean
  public JavaMailSender javaMailSender() {

    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    mailSender.setHost(host);
    mailSender.setPort(port);

    mailSender.setUsername(username);
    mailSender.setPassword(password);

    mailSender.setDefaultEncoding(UTF_8_CHARSET);

    Properties properties = mailSender.getJavaMailProperties();

    properties.putAll(
        Map.of(
            "mail.transport.protocol",
            "smtp",
            "mail.smtp.auth",
            "true",
            "mail.smtp.starttls.enable",
            "true",
            "mail.debug",
            "true",
            "mail.smtp.ssl.trust",
            "smtp.gmail.com",
            "mail.smtp.ssl.protocols",
            "TLSv1.2"));

    return mailSender;
  }
}
