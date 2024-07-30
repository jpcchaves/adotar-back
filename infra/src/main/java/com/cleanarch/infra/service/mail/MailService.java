package com.cleanarch.infra.service.mail;

public interface MailService {

  void send(String subject, String body, String recipient);
}
