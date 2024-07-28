package com.cleanarch.infra.config.security;

import br.com.jpcchaves.core.exception.InternalServerError;
import br.com.jpcchaves.core.exception.enums.ExceptionDefinition;
import com.cleanarch.infra.config.security.constants.RequestMatchersConstants;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

  private static final Logger logger =
      LoggerFactory.getLogger(WebSecurityConfig.class);

  private static final int BCRYPT_SALT_ROUNDS = 8;

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final AccessDeniedHandler accessDeniedHandler;
  private final AuthenticationEntryPoint authenticationEntryPoint;

  public WebSecurityConfig(
      JwtAuthenticationFilter jwtAuthenticationFilter,
      AccessDeniedHandler accessDeniedHandler,
      AuthenticationEntryPoint authenticationEntryPoint) {
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    this.accessDeniedHandler = accessDeniedHandler;
    this.authenticationEntryPoint = authenticationEntryPoint;
  }

  @Bean
  public static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(BCRYPT_SALT_ROUNDS);
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)
      throws Exception {
    try {
      http.formLogin(AbstractHttpConfigurer::disable)
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
                      .requestMatchers(
                          RequestMatchersConstants.PUBLIC_REQUEST_MATCHERS)
                      .permitAll()
                      .anyRequest()
                      .authenticated())
          .exceptionHandling(
              httpSecurityExceptionHandlingConfigurer ->
                  httpSecurityExceptionHandlingConfigurer
                      .accessDeniedHandler(accessDeniedHandler)
                      .authenticationEntryPoint(authenticationEntryPoint))
          .sessionManagement(
              session ->
                  session.sessionCreationPolicy(
                      SessionCreationPolicy.STATELESS))
          .cors(Customizer.withDefaults());

      http.addFilterBefore(
          jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

      return http.build();

    } catch (Exception ex) {

      logger.error(ex.getMessage());
      logger.error(Arrays.toString(ex.getStackTrace()));

      throw new InternalServerError(ExceptionDefinition.INT0001);
    }
  }
}
