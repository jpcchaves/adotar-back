package com.jpcchaves.adotar.service.impl;

import com.jpcchaves.adotar.domain.entities.PasswordResetToken;
import com.jpcchaves.adotar.domain.entities.User;
import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.email.ContactEmailDto;
import com.jpcchaves.adotar.service.usecases.EmailService;
import com.jpcchaves.adotar.service.usecases.SecurityContextService;
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

    public EmailServiceImpl(JavaMailSender javaMailSender, SecurityContextService contextService) {
        this.mailSender = javaMailSender;
        this.contextService = contextService;
    }

    public void sendResetPasswordRequest(PasswordResetToken passwordResetToken) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(passwordResetToken.getUser().getEmail());
        helper.setSubject("Adote.Me - Solcitação para Redefinir Senha");
        helper.setText(generateResetPasswordMessage(passwordResetToken), true);
        mailSender.send(message);
    }

    @Override
    public ApiMessageResponseDto sendContactMessage(ContactEmailDto contactEmailDto) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        User user = contextService.getCurrentLoggedUser();

        helper.setTo(appMail);
        helper.setFrom(user.getEmail());
        helper.setSubject(contactEmailDto.getSubject());
        helper.setText(generateContactUsHtml(contactEmailDto, user), true);

        mailSender.send(message);

        return new ApiMessageResponseDto("Mensagem enviada com sucesso!");
    }

    private String generateResetPasswordMessage(PasswordResetToken passwordResetToken) {
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "  <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\" />\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <meta name=\"color-scheme\" content=\"light dark\" />\n" +
                "    <meta name=\"supported-color-schemes\" content=\"light dark\" />\n" +
                "    <title></title>\n" +
                "    <style type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n" +
                "    /* Base ------------------------------ */\n" +
                "    \n" +
                "    @import url(\"https://fonts.googleapis.com/css?family=Nunito+Sans:400,700&display=swap\");\n" +
                "    body {\n" +
                "      width: 100% !important;\n" +
                "      height: 100%;\n" +
                "      margin: 0;\n" +
                "      -webkit-text-size-adjust: none;\n" +
                "    }\n" +
                "    \n" +
                "    a {\n" +
                "      color: #3869D4;\n" +
                "    }\n" +
                "    \n" +
                "    a img {\n" +
                "      border: none;\n" +
                "    }\n" +
                "    \n" +
                "    td {\n" +
                "      word-break: break-word;\n" +
                "    }\n" +
                "    \n" +
                "    .preheader {\n" +
                "      display: none !important;\n" +
                "      visibility: hidden;\n" +
                "      mso-hide: all;\n" +
                "      font-size: 1px;\n" +
                "      line-height: 1px;\n" +
                "      max-height: 0;\n" +
                "      max-width: 0;\n" +
                "      opacity: 0;\n" +
                "      overflow: hidden;\n" +
                "    }\n" +
                "    /* Type ------------------------------ */\n" +
                "    \n" +
                "    body,\n" +
                "    td,\n" +
                "    th {\n" +
                "      font-family: \"Nunito Sans\", Helvetica, Arial, sans-serif;\n" +
                "    }\n" +
                "    \n" +
                "    h1 {\n" +
                "      margin-top: 0;\n" +
                "      color: #333333;\n" +
                "      font-size: 22px;\n" +
                "      font-weight: bold;\n" +
                "      text-align: left;\n" +
                "    }\n" +
                "    \n" +
                "    h2 {\n" +
                "      margin-top: 0;\n" +
                "      color: #333333;\n" +
                "      font-size: 16px;\n" +
                "      font-weight: bold;\n" +
                "      text-align: left;\n" +
                "    }\n" +
                "    \n" +
                "    h3 {\n" +
                "      margin-top: 0;\n" +
                "      color: #333333;\n" +
                "      font-size: 14px;\n" +
                "      font-weight: bold;\n" +
                "      text-align: left;\n" +
                "    }\n" +
                "    \n" +
                "    td,\n" +
                "    th {\n" +
                "      font-size: 16px;\n" +
                "    }\n" +
                "    \n" +
                "    p,\n" +
                "    ul,\n" +
                "    ol,\n" +
                "    blockquote {\n" +
                "      margin: .4em 0 1.1875em;\n" +
                "      font-size: 16px;\n" +
                "      line-height: 1.625;\n" +
                "    }\n" +
                "    \n" +
                "    p.sub {\n" +
                "      font-size: 13px;\n" +
                "    }\n" +
                "    /* Utilities ------------------------------ */\n" +
                "    \n" +
                "    .align-right {\n" +
                "      text-align: right;\n" +
                "    }\n" +
                "    \n" +
                "    .align-left {\n" +
                "      text-align: left;\n" +
                "    }\n" +
                "    \n" +
                "    .align-center {\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "    \n" +
                "    .u-margin-bottom-none {\n" +
                "      margin-bottom: 0;\n" +
                "    }\n" +
                "    /* Buttons ------------------------------ */\n" +
                "    \n" +
                "    .button {\n" +
                "      background-color: #3869D4;\n" +
                "      border-top: 10px solid #3869D4;\n" +
                "      border-right: 18px solid #3869D4;\n" +
                "      border-bottom: 10px solid #3869D4;\n" +
                "      border-left: 18px solid #3869D4;\n" +
                "      display: inline-block;\n" +
                "      color: #FFF;\n" +
                "      text-decoration: none;\n" +
                "      border-radius: 3px;\n" +
                "      box-shadow: 0 2px 3px rgba(0, 0, 0, 0.16);\n" +
                "      -webkit-text-size-adjust: none;\n" +
                "      box-sizing: border-box;\n" +
                "    }\n" +
                "    \n" +
                "    .button--green {\n" +
                "      background-color: #22BC66;\n" +
                "      border-top: 10px solid #22BC66;\n" +
                "      border-right: 18px solid #22BC66;\n" +
                "      border-bottom: 10px solid #22BC66;\n" +
                "      border-left: 18px solid #22BC66;\n" +
                "    }\n" +
                "    \n" +
                "    .button--red {\n" +
                "      background-color: #FF6136;\n" +
                "      border-top: 10px solid #FF6136;\n" +
                "      border-right: 18px solid #FF6136;\n" +
                "      border-bottom: 10px solid #FF6136;\n" +
                "      border-left: 18px solid #FF6136;\n" +
                "    }\n" +
                "    \n" +
                "    @media only screen and (max-width: 500px) {\n" +
                "      .button {\n" +
                "        width: 100% !important;\n" +
                "        text-align: center !important;\n" +
                "      }\n" +
                "    }\n" +
                "    /* Attribute list ------------------------------ */\n" +
                "    \n" +
                "    .attributes {\n" +
                "      margin: 0 0 21px;\n" +
                "    }\n" +
                "    \n" +
                "    .attributes_content {\n" +
                "      background-color: #F4F4F7;\n" +
                "      padding: 16px;\n" +
                "    }\n" +
                "    \n" +
                "    .attributes_item {\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "    /* Related Items ------------------------------ */\n" +
                "    \n" +
                "    .related {\n" +
                "      width: 100%;\n" +
                "      margin: 0;\n" +
                "      padding: 25px 0 0 0;\n" +
                "      -premailer-width: 100%;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "    }\n" +
                "    \n" +
                "    .related_item {\n" +
                "      padding: 10px 0;\n" +
                "      color: #CBCCCF;\n" +
                "      font-size: 15px;\n" +
                "      line-height: 18px;\n" +
                "    }\n" +
                "    \n" +
                "    .related_item-title {\n" +
                "      display: block;\n" +
                "      margin: .5em 0 0;\n" +
                "    }\n" +
                "    \n" +
                "    .related_item-thumb {\n" +
                "      display: block;\n" +
                "      padding-bottom: 10px;\n" +
                "    }\n" +
                "    \n" +
                "    .related_heading {\n" +
                "      border-top: 1px solid #CBCCCF;\n" +
                "      text-align: center;\n" +
                "      padding: 25px 0 10px;\n" +
                "    }\n" +
                "    /* Discount Code ------------------------------ */\n" +
                "    \n" +
                "    .discount {\n" +
                "      width: 100%;\n" +
                "      margin: 0;\n" +
                "      padding: 24px;\n" +
                "      -premailer-width: 100%;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "      background-color: #F4F4F7;\n" +
                "      border: 2px dashed #CBCCCF;\n" +
                "    }\n" +
                "    \n" +
                "    .discount_heading {\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "    \n" +
                "    .discount_body {\n" +
                "      text-align: center;\n" +
                "      font-size: 15px;\n" +
                "    }\n" +
                "    /* Social Icons ------------------------------ */\n" +
                "    \n" +
                "    .social {\n" +
                "      width: auto;\n" +
                "    }\n" +
                "    \n" +
                "    .social td {\n" +
                "      padding: 0;\n" +
                "      width: auto;\n" +
                "    }\n" +
                "    \n" +
                "    .social_icon {\n" +
                "      height: 20px;\n" +
                "      margin: 0 8px 10px 8px;\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "    /* Data table ------------------------------ */\n" +
                "    \n" +
                "    .purchase {\n" +
                "      width: 100%;\n" +
                "      margin: 0;\n" +
                "      padding: 35px 0;\n" +
                "      -premailer-width: 100%;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "    }\n" +
                "    \n" +
                "    .purchase_content {\n" +
                "      width: 100%;\n" +
                "      margin: 0;\n" +
                "      padding: 25px 0 0 0;\n" +
                "      -premailer-width: 100%;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "    }\n" +
                "    \n" +
                "    .purchase_item {\n" +
                "      padding: 10px 0;\n" +
                "      color: #51545E;\n" +
                "      font-size: 15px;\n" +
                "      line-height: 18px;\n" +
                "    }\n" +
                "    \n" +
                "    .purchase_heading {\n" +
                "      padding-bottom: 8px;\n" +
                "      border-bottom: 1px solid #EAEAEC;\n" +
                "    }\n" +
                "    \n" +
                "    .purchase_heading p {\n" +
                "      margin: 0;\n" +
                "      color: #85878E;\n" +
                "      font-size: 12px;\n" +
                "    }\n" +
                "    \n" +
                "    .purchase_footer {\n" +
                "      padding-top: 15px;\n" +
                "      border-top: 1px solid #EAEAEC;\n" +
                "    }\n" +
                "    \n" +
                "    .purchase_total {\n" +
                "      margin: 0;\n" +
                "      text-align: right;\n" +
                "      font-weight: bold;\n" +
                "      color: #333333;\n" +
                "    }\n" +
                "    \n" +
                "    .purchase_total--label {\n" +
                "      padding: 0 15px 0 0;\n" +
                "    }\n" +
                "    \n" +
                "    body {\n" +
                "      background-color: #F2F4F6;\n" +
                "      color: #51545E;\n" +
                "    }\n" +
                "    \n" +
                "    p {\n" +
                "      color: #51545E;\n" +
                "    }\n" +
                "    \n" +
                "    .email-wrapper {\n" +
                "      width: 100%;\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "      -premailer-width: 100%;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "      background-color: #F2F4F6;\n" +
                "    }\n" +
                "    \n" +
                "    .email-content {\n" +
                "      width: 100%;\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "      -premailer-width: 100%;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "    }\n" +
                "    /* Masthead ----------------------- */\n" +
                "    \n" +
                "    .email-masthead {\n" +
                "      padding: 25px 0;\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "    \n" +
                "    .email-masthead_logo {\n" +
                "      width: 94px;\n" +
                "    }\n" + "    \n" +
                "    .token-container {\n" +
                "      padding: 10px 30px;\n" +
                "      background-color: #22bc66;\n" +
                "      border-radius: 5px;\n" +
                "      width: 90px;\n" +
                "      letter-spacing: 0.2rem;\n" +
                "      color: #fff;\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "    \n" +
                "    .email-masthead_name {\n" +
                "      font-size: 16px;\n" +
                "      font-weight: bold;\n" +
                "      color: #A8AAAF;\n" +
                "      text-decoration: none;\n" +
                "      text-shadow: 0 1px 0 white;\n" +
                "    }\n" +
                "    /* Body ------------------------------ */\n" +
                "    \n" +
                "    .email-body {\n" +
                "      width: 100%;\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "      -premailer-width: 100%;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "    }\n" +
                "    \n" +
                "    .email-body_inner {\n" +
                "      width: 570px;\n" +
                "      margin: 0 auto;\n" +
                "      padding: 0;\n" +
                "      -premailer-width: 570px;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "      background-color: #FFFFFF;\n" +
                "    }\n" +
                "    \n" +
                "    .email-footer {\n" +
                "      width: 570px;\n" +
                "      margin: 0 auto;\n" +
                "      padding: 0;\n" +
                "      -premailer-width: 570px;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "    \n" +
                "    .email-footer p {\n" +
                "      color: #A8AAAF;\n" +
                "    }\n" +
                "    \n" +
                "    .body-action {\n" +
                "      width: 100%;\n" +
                "      margin: 30px auto;\n" +
                "      padding: 0;\n" +
                "      -premailer-width: 100%;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "    \n" +
                "    .body-sub {\n" +
                "      margin-top: 25px;\n" +
                "      padding-top: 25px;\n" +
                "      border-top: 1px solid #EAEAEC;\n" +
                "    }\n" +
                "    \n" +
                "    .content-cell {\n" +
                "      padding: 45px;\n" +
                "    }\n" +
                "    /*Media Queries ------------------------------ */\n" +
                "    \n" +
                "    @media only screen and (max-width: 600px) {\n" +
                "      .email-body_inner,\n" +
                "      .email-footer {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "    }\n" +
                "    \n" +
                "    @media (prefers-color-scheme: dark) {\n" +
                "      body,\n" +
                "      .email-body,\n" +
                "      .email-body_inner,\n" +
                "      .email-content,\n" +
                "      .email-wrapper,\n" +
                "      .email-masthead,\n" +
                "      .email-footer {\n" +
                "        background-color: #333333 !important;\n" +
                "        color: #FFF !important;\n" +
                "      }\n" +
                "      p,\n" +
                "      ul,\n" +
                "      ol,\n" +
                "      blockquote,\n" +
                "      h1,\n" +
                "      h2,\n" +
                "      h3,\n" +
                "      span,\n" +
                "      .purchase_item {\n" +
                "        color: #FFF !important;\n" +
                "      }\n" +
                "      .attributes_content,\n" +
                "      .discount {\n" +
                "        background-color: #222 !important;\n" +
                "      }\n" +
                "      .email-masthead_name {\n" +
                "        text-shadow: none !important;\n" +
                "      }\n" +
                "    }\n" +
                "    \n" +
                "    :root {\n" +
                "      color-scheme: light dark;\n" +
                "      supported-color-schemes: light dark;\n" +
                "    }\n" +
                "    </style>\n" +
                "    <!--[if mso]>\n" +
                "    <style type=\"text/css\">\n" +
                "      .f-fallback  {\n" +
                "        font-family: Arial, sans-serif;\n" +
                "      }\n" +
                "    </style>\n" +
                "  <![endif]-->\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <span class=\"preheader\">Use o código para redefinir sua senha. O código é válido por 5 minutos.</span>\n" +
                "    <table class=\"email-wrapper\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "      <tr>\n" +
                "        <td align=\"center\">\n" +
                "          <table class=\"email-content\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "            <tr>\n" +
                "              <td class=\"email-masthead\">\n" +
                "                <a href=\"https://example.com\" class=\"f-fallback email-masthead_name\">\n" +
                "                Adote.Me\n" +
                "              </a>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <!-- Email Body -->\n" +
                "            <tr>\n" +
                "              <td class=\"email-body\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                <table class=\"email-body_inner\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                  <!-- Body content -->\n" +
                "                  <tr>\n" +
                "                    <td class=\"content-cell\">\n" +
                "                      <div class=\"f-fallback\">\n" +
                "                        <h1>Olá, " + passwordResetToken.getUser().getFirstName() + "!</h1>\n" +
                "                        <p>Você solicitou uma redefinição de senha. Use o código abaixo para redefinir a sua senha. <strong>O código é válido por 5 minutos.</strong></p>\n" +
                "                        <!-- Action -->\n" +
                "                        <table class=\"body-action\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                          <tr>\n" +
                "                            <td align=\"center\">\n" +
                "                              <!-- Border based button\n" +
                "           https://litmus.com/blog/a-guide-to-bulletproof-buttons-in-email-design -->\n" +
                "                              <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\n" +
                "                                <tr>\n" +
                "                                  <td align=\"center\">\n" +
                "                                    <div class=\"token-container\">" + passwordResetToken.getToken() + "</div>\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                              </table>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </table>\n" +
                "                        <p>Se você não realizou essa solicitação, por favor, ignore esse email.</p>\n" +
                "                        <p>Atenciosamente,\n" +
                "                          <br>Time Adote.Me</p>\n" +
                "                      </div>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </table>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td>\n" +
                "                <table class=\"email-footer\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                  <tr>\n" +
                "                    <td class=\"content-cell\" align=\"center\">\n" +
                "                      <p class=\"f-fallback sub align-center\">\n" +
                "                        [Adote.ME, BR]\n" +
                "                        <br>Brasil\n" +
                "                      </p>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </table>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "  </body>\n" +
                "</html>";
    }

    private String generateContactUsHtml(ContactEmailDto contactEmailDto, User user) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\"\n" +
                "  xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "\n" +
                "<head>\n" +
                "  <title> Contato </title>\n" +
                "\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
                "\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" +
                "  <style type=\"text/css\">\n" +
                "    #outlook a {\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "\n" +
                "    body {\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "      -webkit-text-size-adjust: 100%;\n" +
                "      -ms-text-size-adjust: 100%;\n" +
                "    }\n" +
                "\n" +
                "    table,\n" +
                "    td {\n" +
                "      border-collapse: collapse;\n" +
                "      mso-table-lspace: 0pt;\n" +
                "      mso-table-rspace: 0pt;\n" +
                "    }\n" +
                "\n" +
                "    img {\n" +
                "      border: 0;\n" +
                "      height: auto;\n" +
                "      line-height: 100%;\n" +
                "      outline: none;\n" +
                "      text-decoration: none;\n" +
                "      -ms-interpolation-mode: bicubic;\n" +
                "    }\n" +
                "\n" +
                "    p {\n" +
                "      display: block;\n" +
                "      margin: 13px 0;\n" +
                "    }\n" +
                "  </style>\n" +
                "\n" +
                "  <!--<xml>\n" +
                "    <o:OfficeDocumentSettings>\n" +
                "      <o:AllowPNG />\n" +
                "      <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "    </o:OfficeDocumentSettings>\n" +
                "  </xml>\n" +
                "-->\n" +
                "\n" +
                "  <style type=\"text/css\">\n" +
                "    .mj-outlook-group-fix {\n" +
                "      width: 100% !important;\n" +
                "    }\n" +
                "  </style>\n" +
                "\n" +
                "  <style type=\"text/css\">\n" +
                "    @media only screen and (min-width:480px) {\n" +
                "      .mj-column-per-100 {\n" +
                "        width: 100% !important;\n" +
                "        max-width: 100%;\n" +
                "      }\n" +
                "    }\n" +
                "  </style>\n" +
                "  <style type=\"text/css\">\n" +
                "    @media only screen and (max-width:480px) {\n" +
                "      table.mj-full-width-mobile {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "\n" +
                "      td.mj-full-width-mobile {\n" +
                "        width: auto !important;\n" +
                "      }\n" +
                "    }\n" +
                "  </style>\n" +
                "  <style type=\"text/css\">\n" +
                "    a,\n" +
                "    span,\n" +
                "    td,\n" +
                "    th {\n" +
                "      -webkit-font-smoothing: antialiased !important;\n" +
                "      -moz-osx-font-smoothing: grayscale !important;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"background-color:#ffffff;\">\n" +
                "  <div\n" +
                "    style=\"display:none;font-size:1px;color:#ffffff;line-height:1px;max-height:0px;max-width:0px;opacity:0;overflow:hidden;\">\n" +
                "    Preview - Welcome to Coded Mails </div>\n" +
                "  <div style=\"background-color:#ffffff;\">\n" +
                "\n" +
                "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\">\n" +
                "      <tr>\n" +
                "        <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "\n" +
                "          <div style=\"margin:0px auto;max-width:600px;\">\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                  <td style=\"direction:ltr;font-size:0px;padding:20px 0;padding-bottom:0px;text-align:center;\">\n" +
                "\n" +
                "                    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "\n" +
                "                      <tr>\n" +
                "\n" +
                "                        <td class=\"\" style=\"vertical-align:top;width:600px;\">\n" +
                "\n" +
                "                          <div class=\"mj-column-per-100 mj-outlook-group-fix\"\n" +
                "                            style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"\n" +
                "                              style=\"vertical-align:top;\" width=\"100%\">\n" +
                "                              <tbody>\n" +
                "                                <tr>\n" +
                "                                  <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"\n" +
                "                                      style=\"border-collapse:collapse;border-spacing:0px;\">\n" +
                "                                      <tbody>\n" +
                "                                        <tr>\n" +
                "                                          <td style=\"width:50px;\">\n" +
                "                                            <img alt=\"image description\" height=\"auto\"\n" +
                "                                              style=\"border:0;display:block;outline:none;text-decoration:none;height:auto;width:100%;font-size:14px;\"\n" +
                "                                              src=\"https://i.ibb.co/P61Kcfk/IMG-20230801-WA0028.jpg\"\n" +
                "                                              width=\"50\" />\n" +
                "                                          </td>\n" +
                "                                        </tr>\n" +
                "                                      </tbody>\n" +
                "                                    </table>\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                  <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                                    <div\n" +
                "                                      style=\"font-family:Helvetica, Arial, sans-serif;font-size:18px;font-weight:400;line-height:24px;text-align:left;color:#434245;\">\n" +
                "                                      <h1 style=\"margin: 0; font-size: 24px; line-height: normal; font-weight: bold;\">\n" +
                "                                        Nova mensagem\n" +
                "                                      </h1>\n" +
                "                                    </div>\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                              </tbody>\n" +
                "                            </table>\n" +
                "                          </div>\n" +
                "\n" +
                "                        </td>\n" +
                "\n" +
                "                      </tr>\n" +
                "\n" +
                "                    </table>\n" +
                "\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </div>\n" +
                "\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "\n" +
                "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\">\n" +
                "      <tr>\n" +
                "        <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "\n" +
                "          <div style=\"margin:0px auto;max-width:600px;\">\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                  <td style=\"direction:ltr;font-size:0px;padding:20px 0;text-align:center;\">\n" +
                "\n" +
                "                    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "\n" +
                "                      <tr>\n" +
                "\n" +
                "                        <td class=\"\" style=\"vertical-align:top;width:600px;\">\n" +
                "\n" +
                "                          <div class=\"mj-column-per-100 mj-outlook-group-fix\"\n" +
                "                            style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"\n" +
                "                              style=\"vertical-align:top;\" width=\"100%\">\n" +
                "                              <tbody>\n" +
                "                                <tr>\n" +
                "                                  <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                                    <div\n" +
                "                                      style=\"font-family:Helvetica, Arial, sans-serif;font-size:18px;font-weight:400;line-height:24px;text-align:left;color:#434245;\">\n" +
                "                                      <p style=\"margin: 0;\">Olá!</p>\n" +
                "                                    </div>\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                  <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                                    <div\n" +
                "                                      style=\"font-family:Helvetica, Arial, sans-serif;font-size:18px;font-weight:400;line-height:24px;text-align:left;color:#434245;\">\n" +
                "                                      <p style=\"margin: 0;\">Você tem uma nova mensagem</p>\n" +
                "                                    </div>\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                              </tbody>\n" +
                "                            </table>\n" +
                "                          </div>\n" +
                "\n" +
                "                        </td>\n" +
                "\n" +
                "                      </tr>\n" +
                "\n" +
                "                    </table>\n" +
                "\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </div>\n" +
                "\n" +
                "\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "\n" +
                "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\">\n" +
                "      <tr>\n" +
                "        <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "\n" +
                "          <div style=\"background:#BFFCFD;background-color:#BFFCFD;margin:0px auto;border-radius:4px;max-width:600px;\">\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"\n" +
                "              style=\"background:#BFFCFD;background-color:#BFFCFD;width:100%;border-radius:4px;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                  <td style=\"direction:ltr;font-size:0px;padding:20px 0;text-align:center;\">\n" +
                "\n" +
                "                    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "\n" +
                "                      <tr>\n" +
                "\n" +
                "                        <td class=\"\" style=\"vertical-align:top;width:600px;\">\n" +
                "\n" +
                "                          <div class=\"mj-column-per-100 mj-outlook-group-fix\"\n" +
                "                            style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"\n" +
                "                              style=\"vertical-align:top;\" width=\"100%\">\n" +
                "                              <tbody>\n" +
                "                                <tr>\n" +
                "                                  <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                                    <div\n" +
                "                                      style=\"font-family:Helvetica, Arial, sans-serif;font-size:18px;font-weight:bold;line-height:24px;text-align:left;color:#053878;\">\n" +
                "                                      <p class=\"date\" style=\"margin: 0; margin-bottom: 5px; font-size: 16px;\">" + contactEmailDto.getSubject() + "</p>\n" +
                "                                    </div>\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                  <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                                    <div\n" +
                "                                      style=\"font-family:Helvetica, Arial, sans-serif;font-size:18px;font-weight:400;line-height:24px;text-align:left;color:#053878;\">\n" +
                "                                      <p style=\"margin: 0;\">" + contactEmailDto.getMessage() +
                "                                      </p>\n" +
                "                                    </div>\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                              </tbody>\n" +
                "                            </table>\n" +
                "                          </div>\n" +
                "\n" +
                "                        </td>\n" +
                "\n" +
                "                      </tr>\n" +
                "\n" +
                "                    </table>\n" +
                "\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </div>\n" +
                "\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "\n" +
                "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\">\n" +
                "      <tr>\n" +
                "        <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "\n" +
                "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\">\n" +
                "      <tr>\n" +
                "        <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "\n" +
                "          <div style=\"margin:0px auto;max-width:600px;\">\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                  <td style=\"direction:ltr;font-size:0px;padding:20px 0;text-align:center;\">\n" +
                "\n" +
                "                    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "\n" +
                "                      <tr>\n" +
                "\n" +
                "                        <td class=\"\" style=\"vertical-align:top;width:600px;\">\n" +
                "\n" +
                "                          <div class=\"mj-column-per-100 mj-outlook-group-fix\"\n" +
                "                            style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"\n" +
                "                              style=\"vertical-align:top;\" width=\"100%\">\n" +
                "                              <tbody>\n" +
                "                                <tr>\n" +
                "                                  <td style=\"font-size:0px;word-break:break-word;\">\n" +
                "\n" +
                "\n" +
                "                                    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                                      <tr>\n" +
                "                                        <td height=\"1\" style=\"vertical-align:top;height:1px;\">\n" +
                "\n" +
                "\n" +
                "                                          <div style=\"height:1px;\">   </div>\n" +
                "\n" +
                "\n" +
                "                                        </td>\n" +
                "                                      </tr>\n" +
                "                                    </table>\n" +
                "\n" +
                "\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                              </tbody>\n" +
                "                            </table>\n" +
                "                          </div>\n" +
                "\n" +
                "                        </td>\n" +
                "\n" +
                "                      </tr>\n" +
                "\n" +
                "                    </table>\n" +
                "\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </div>\n" +
                "\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "\n" +
                "  </div>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }
}
