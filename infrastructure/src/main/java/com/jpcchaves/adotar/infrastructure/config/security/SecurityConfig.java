package com.jpcchaves.adotar.infrastructure.config.security;

import com.jpcchaves.adotar.infrastructure.presentation.error.CustomAccessDeniedHandler;
import com.jpcchaves.adotar.infrastructure.presentation.security.JwtAuthenticationEntrypoint;
import com.jpcchaves.adotar.infrastructure.presentation.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  private final JwtAuthenticationFilter authenticationFilter;
  private final JwtAuthenticationEntrypoint authenticationEntryPoint;
  private final CustomAccessDeniedHandler customAccessDeniedHandler;

  public SecurityConfig(
      JwtAuthenticationFilter authenticationFilter,
      JwtAuthenticationEntrypoint authenticationEntryPoint,
      CustomAccessDeniedHandler customAccessDeniedHandler) {
    this.authenticationFilter = authenticationFilter;
    this.authenticationEntryPoint = authenticationEntryPoint;
    this.customAccessDeniedHandler = customAccessDeniedHandler;
  }

  @Bean
  public static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .headers(
            (headers) ->
                headers
                    .contentTypeOptions(Customizer.withDefaults())
                    .xssProtection(Customizer.withDefaults())
                    .cacheControl(Customizer.withDefaults())
                    .httpStrictTransportSecurity(Customizer.withDefaults())
                    .frameOptions(Customizer.withDefaults())
                    .disable())
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            authorize ->
                authorize
                    .requestMatchers(
                        "/api/v2/auth/login",
                        "/api/v1/auth/register",
                        "/api/v1/auth/verify-token",
                        "/api/v1/forgot-password/**",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/h2/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .exceptionHandling(
            exception ->
                exception
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .accessDeniedHandler(customAccessDeniedHandler))
        .sessionManagement(
            session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .cors(Customizer.withDefaults());

    http.addFilterBefore(
        authenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
