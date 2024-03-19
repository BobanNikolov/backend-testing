package com.backend.testing.service.measurement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MeasurementPersistCommand {
  @NotNull
  private String sensorId;
  @NotNull
  private OffsetDateTime stamp;
  @NotNull
  private String valueType;
  @NotNull
  private String value;
}
