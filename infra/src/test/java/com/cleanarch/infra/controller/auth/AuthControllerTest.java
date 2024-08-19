package com.cleanarch.infra.controller.auth;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import br.com.jpcchaves.core.domain.constants.HttpStatus;
import com.cleanarch.infra.config.contants.TestsConfigConstants;
import com.cleanarch.infra.config.testcontainer.AbstractTestContainerConfig;
import com.cleanarch.infra.domain.dto.auth.PasswordResetRequestDTO;
import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@TestPropertySource(locations = {"classpath:application-test.yml", "classpath:.env"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class AuthControllerTest extends AbstractTestContainerConfig {

  private static RequestSpecification requestSpecification;
  private static ObjectMapper mapper;
  private static Faker faker;

  @BeforeAll
  static void setup() {

    faker = new Faker();

    mapper = new ObjectMapper();

    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    requestSpecification =
        new RequestSpecBuilder()
            .setBasePath("/api/v1/auth")
            .setPort(TestsConfigConstants.SERVER_PORT)
            .addFilter(new RequestLoggingFilter(LogDetail.ALL))
            .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
            .build();
  }

  @DisplayName(
      "Integration test given user email in PasswordResetRequestDTO object when request a password reset then should create a password reset token and send by mail")
  @Test
  void
      integrationTestGivenUserEmailInPasswordResetRequestDTOObjectWhenRequestPassResetThenShouldCreatePassResetToken()
          throws IOException {

    String response =
        given()
            .spec(requestSpecification)
            .contentType(TestsConfigConstants.CONTENT_TYPE_JSON)
            .body(new PasswordResetRequestDTO("testesadotar@outlook.com"))
            .when()
            .post("/password-reset")
            .then()
            .statusCode(HttpStatus.OK)
            .extract()
            .body()
            .asString();

    MessageResponseDTO responseDto = mapper.readValue(response, MessageResponseDTO.class);

    assertNotNull(responseDto);
    assertEquals("Password request sent!", responseDto.getMessage());
  }
}
