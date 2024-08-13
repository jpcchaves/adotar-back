package com.cleanarch.infra.config.testcontainer;

import java.util.Map;
import java.util.stream.Stream;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

@ContextConfiguration(
    initializers = AbstractTestContainerConfig.Initializer.class)
public abstract class AbstractTestContainerConfig {

  public static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    static PostgreSQLContainer<?> pgsql =
        new PostgreSQLContainer<>("postgres:12");

    private static void startContainers() {

      Startables.deepStart(Stream.of(pgsql)).join();
    }

    private static Map<String, Object> configureConnection() {

      return Map.of(
          "spring.datasource.url", pgsql.getJdbcUrl(),
          "spring.datasource.username", pgsql.getUsername(),
          "spring.datasource.password", pgsql.getPassword());
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

      startContainers();

      ConfigurableEnvironment environment = applicationContext.getEnvironment();

      MapPropertySource testContainers =
          new MapPropertySource("testcontainers", configureConnection());

      environment.getPropertySources().addFirst(testContainers);
    }
  }
}
