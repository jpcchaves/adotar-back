package com.cleanarch.infra;

import com.cleanarch.infra.service.mail.MailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = {"classpath:.env"})
public class MailServiceTest {

  @Autowired private MailService mailService;

  @DisplayName("Test send email with HTML body")
  @Test
  void testEmailSend_When_SendingEmailWithHTMLBody_ShouldArriveToRecipient()
      throws InterruptedException {

    // Given / Arrange
    String recipient = "jpcchaves@outlook.com";

    // When / Act
    mailService.send("", "<h1>test email</h1>", recipient);

    Thread.sleep(10000);

    // Then / Assert
  }
}
