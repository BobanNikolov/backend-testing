package com.backend.testing.endpoint.measurement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementOut {
  private String sensorId;
  private String position;
  private String stamp;
  private Integer year;
  private String valueType;
  private String value;
}
