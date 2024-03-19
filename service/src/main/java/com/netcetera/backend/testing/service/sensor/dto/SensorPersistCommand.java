package com.netcetera.backend.testing.service.sensor.dto;

import com.netcetera.backend.testing.service.sensor.dto.validator.CheckIfSensorTypeExistsValidation;
import com.netcetera.backend.testing.service.sensor.dto.validator.CheckIfStatusExistsValidation;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@CheckIfSensorTypeExistsValidation
@CheckIfStatusExistsValidation
public class SensorPersistCommand {
  @NotNull
  private String position;
  @NotNull
  private String sensorType;
  @NotNull
  private String description;
  @NotNull
  private String comments;
  @NotNull
  private String status;
}