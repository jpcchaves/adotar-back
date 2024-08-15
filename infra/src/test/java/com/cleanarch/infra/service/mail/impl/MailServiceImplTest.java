package com.cleanarch.infra.service.mail.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import com.cleanarch.infra.config.testcontainer.AbstractTestContainerConfig;
import com.cleanarch.infra.service.mail.MailService;
import com.cleanarch.infra.service.mail.MailTemplates;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = {"classpath:.env"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MailServiceImplTest extends AbstractTestContainerConfig {

  @Autowired private MailService mailService;

  @Autowired private MailTemplates mailTemplates;

  @DisplayName("Test send email with HTML body")
  @Test
  void testEmailSend_When_SendingEmailWithHTMLBody_ShouldArriveToRecipient() {

    // Given / Arrange
    String recipient = "jpcchaves@outlook.com";

    // When / Act
    mailService.send(
        "Welcome to Adotar!", mailTemplates.getRegisterSuccessfulTemplate("Joao"), recipient);

    // Then / Assert
  }

  @DisplayName("Test send email with HTML body using CompletableFuture")
  @Test
  void testEmailSend_When_SendingEmailWithCompletableFuture_ShouldArriveToRecipient() {

    // Given
    String recipient = "jpcchaves@outlook.com";

    CompletableFuture<Boolean> future =
        mailService.sendEmail(
            "Welcome to Adotar!",
            mailTemplates.getRegisterSuccessfulTemplate("Joao Paulo"),
            recipient);

    await()
        .untilAsserted(
            () -> {
              assertThat(future).isCompletedWithValue(true);
            });
  }
}
