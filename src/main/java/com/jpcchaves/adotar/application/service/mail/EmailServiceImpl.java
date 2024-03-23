package com.jpcchaves.adotar.application.service.mail;

import com.jpcchaves.adotar.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.application.dto.email.ContactEmailDto;
import com.jpcchaves.adotar.application.service.mail.contracts.EmailService;
import com.jpcchaves.adotar.application.service.usecases.SecurityContextService;
import com.jpcchaves.adotar.application.utils.mail.MailUtils;
import com.jpcchaves.adotar.domain.model.PasswordResetToken;
import com.jpcchaves.adotar.domain.model.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
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
    MimeMessage message = mailSender.createMimeMessage();

    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

    helper.setTo(passwordResetToken.getUser().getEmail());
    helper.setSubject("Adotar - Solcitação para Redefinir Senha");
    helper.setText(
        MailUtils.generateResetPasswordMessage(passwordResetToken), true);
    mailSender.send(message);
  }

  @Override
  public ApiMessageResponseDto sendContactMessage(
      ContactEmailDto contactEmailDto) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();

    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

    User user = contextService.getCurrentLoggedUser();

    helper.setTo(appMail);
    helper.setFrom(user.getEmail());
    helper.setSubject(contactEmailDto.getSubject());
    helper.setText(MailUtils.generateContactUsHtml(contactEmailDto), true);

    mailSender.send(message);

    return new ApiMessageResponseDto("Mensagem enviada com sucesso!");
  }
}
