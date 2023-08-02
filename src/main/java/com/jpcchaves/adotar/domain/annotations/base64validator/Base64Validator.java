package com.jpcchaves.adotar.domain.annotations.base64validator;

import com.jpcchaves.adotar.utils.base64.Base64Utils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Base64;

public class Base64Validator implements ConstraintValidator<ValidBase64, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        try {
            String base64;

            if (Base64Utils.hasBase64Prefix(value)) {
                base64 = Base64Utils.removeBase64Prefix(value);
            } else {
                base64 = value;
            }

            Base64.getDecoder().decode(base64);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
