package com.cleanarch.infra.config.security;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.*;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.*;
import org.springframework.security.web.*;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .csrf(AbstractHttpConfigurer::disable)
        .headers(
            (headers) ->
                headers
                    .contentTypeOptions(Customizer.withDefaults())
                    .xssProtection(Customizer.withDefaults())
                    .cacheControl(Customizer.withDefaults())
                    .httpStrictTransportSecurity(Customizer.withDefaults())
                    .frameOptions(Customizer.withDefaults())
                    .disable())
        .authorizeHttpRequests(
            authorize ->
                authorize
                    .requestMatchers("/h2/**", "/h2-console/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
        )
        .sessionManagement(session ->
                               session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

    return http.build();
  }
}
