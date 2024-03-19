package com.backend.testing.service.sensor.dto.validator;

import com.backend.testing.model.domain.enums.Status;
import com.backend.testing.service.sensor.dto.SensorPersistCommand;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckIfStatusExistValidator
    implements ConstraintValidator<CheckIfStatusExistsValidation, SensorPersistCommand> {

  @Override
  public boolean isValid(final SensorPersistCommand value, final ConstraintValidatorContext context) {
    return Status.getAllValues().contains(value.getStatus());
  }
}
