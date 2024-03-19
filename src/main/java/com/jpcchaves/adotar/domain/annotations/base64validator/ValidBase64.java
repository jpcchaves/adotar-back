package com.jpcchaves.adotar.domain.annotations.base64validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = Base64Validator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBase64 {
  String message() default
      "O formato do Base64 informado é inválido! Verifique o valor informado e tente novamente. Certifique-se de enviar o Base64 com prefixo.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
