package com.netcetera.backend.testing.service.sensor.dto.validator;

import com.netcetera.backend.testing.model.domain.enums.SensorType;
import com.netcetera.backend.testing.service.sensor.dto.SensorPersistCommand;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckIfSensorTypeExistValidator
    implements ConstraintValidator<CheckIfSensorTypeExistsValidation, SensorPersistCommand> {

  @Override
  public boolean isValid(final SensorPersistCommand value, final ConstraintValidatorContext context) {
    return SensorType.getAllValues().contains(value.getSensorType());
  }
}
