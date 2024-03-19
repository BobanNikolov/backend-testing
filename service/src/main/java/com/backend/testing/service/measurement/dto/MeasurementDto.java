package com.backend.testing.service.measurement.dto;

import com.backend.testing.model.domain.enums.ValueType;
import com.backend.testing.service.sensor.dto.SensorDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MeasurementDto {
  private String measurementId;
  private SensorDto sensor;
  private OffsetDateTime stamp;
  private ValueType valueType;
  private String value;
}
