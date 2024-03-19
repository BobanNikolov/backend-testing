package com.backend.testing.service.sensor.dto.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckIfStatusExistValidator.class)
public @interface CheckIfStatusExistsValidation {
  String message() default "There is no such status!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
