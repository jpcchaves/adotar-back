package com.jpcchaves.adotar.config.timezone;

import jakarta.annotation.PostConstruct;
import java.util.TimeZone;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringTimezoneConfig {

  @PostConstruct
  public void timezoneConfiguration() {
    final String AMERICA_SP_TIMEZONE = "America/Sao_Paulo";
    TimeZone.setDefault(TimeZone.getTimeZone(AMERICA_SP_TIMEZONE));
  }
}
