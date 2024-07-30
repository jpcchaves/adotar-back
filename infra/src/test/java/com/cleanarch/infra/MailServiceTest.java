package com.cleanarch.infra;

import com.cleanarch.infra.service.mail.MailService;
import com.cleanarch.infra.service.mail.MailTemplates;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = {"classpath:.env"})
public class MailServiceTest {

  @Autowired private MailService mailService;

  @Autowired private MailTemplates mailTemplates;

  @DisplayName("Test send email with HTML body")
  @Test
  void testEmailSend_When_SendingEmailWithHTMLBody_ShouldArriveToRecipient() {

    // Given / Arrange
    String recipient = "jpcchaves@outlook.com";

    // When / Act
    mailService.send(
        "Welcome to Adotar!",
        mailTemplates.getRegisterSuccessfulTemplate("Joao"),
        recipient);

    // Then / Assert
  }
}
