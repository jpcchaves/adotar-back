package com.jpcchaves.adotar.config.timezone;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class SpringTimezoneConfig {

    @PostConstruct
    public void timezoneConfiguration() {
        final String AMERICA_SP_TIMEZONE = "America/Sao_Paulo";
        TimeZone.setDefault(TimeZone.getTimeZone(AMERICA_SP_TIMEZONE));
    }
}
