package com.cleanarch.infra.service.mail;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Component
public class MailTemplates {

  private final SpringTemplateEngine templateEngine;

  public MailTemplates(SpringTemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  public String getRegisterSuccessfulTemplate(String name) {

    Context context = new Context();

    context.setVariable("name", name);

    String htmlTemplate = templateEngine.process("registerSuccess", context);

    return htmlTemplate;
  }
}
