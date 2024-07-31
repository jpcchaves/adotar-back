package com.cleanarch.infra.service.mail;

import java.util.concurrent.CompletableFuture;

public interface MailService {

  void send(String subject, String body, String recipient);

  CompletableFuture<Boolean> sendEmail(
      String subject, String body, String recipient);
}
