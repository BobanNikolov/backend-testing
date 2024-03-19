package com.netcetera.backend.testing.service.sensor.dto.validator;

import com.netcetera.backend.testing.model.domain.enums.SensorType;
import com.netcetera.backend.testing.model.domain.enums.Status;
import com.netcetera.backend.testing.service.sensor.dto.SensorPersistCommand;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class CheckIfStatusExistValidator
    implements ConstraintValidator<CheckIfStatusExistsValidation, SensorPersistCommand> {

  @Override
  public boolean isValid(final SensorPersistCommand value, final ConstraintValidatorContext context) {
    return Status.getAllValues().contains(value.getStatus());
  }
}
