package com.jpcchaves.adotar.application.service.mail;

import com.jpcchaves.adotar.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.application.dto.email.ContactEmailDto;
import com.jpcchaves.adotar.application.service.auth.contracts.SecurityContextService;
import com.jpcchaves.adotar.application.service.mail.contracts.EmailService;
import com.jpcchaves.adotar.application.utils.mail.MailUtils;
import com.jpcchaves.adotar.domain.model.PasswordResetToken;
import com.jpcchaves.adotar.domain.model.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
  private final JavaMailSender mailSender;
  private final SecurityContextService contextService;

  @Value("${mail.username}")
  private String appMail;

  public EmailServiceImpl(
      JavaMailSender javaMailSender, SecurityContextService contextService) {
    this.mailSender = javaMailSender;
    this.contextService = contextService;
  }

  public void sendResetPasswordRequest(PasswordResetToken passwordResetToken)
      throws MessagingException {
    mailSender.send(
        prepareEmail(
            passwordResetToken.getUser().getEmail(),
            appMail,
            "Adotar - Redefinir senha",
            MailUtils.generateResetPasswordMessage(passwordResetToken)));
  }

  @Override
  public ApiMessageResponseDto sendContactMessage(
      ContactEmailDto contactEmailDto) throws MessagingException {
    User user = contextService.getCurrentLoggedUser();

    mailSender.send(
        prepareEmail(
            appMail,
            user.getEmail(),
            contactEmailDto.getSubject(),
            MailUtils.generateContactUsHtml(contactEmailDto)));

    return new ApiMessageResponseDto("Mensagem enviada com sucesso!");
  }

  private MimeMessage prepareEmail(
      String to, String from, String subject, String text)
      throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper =
        new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

    helper.setFrom(from);
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(text, true);

    return message;
  }
}
