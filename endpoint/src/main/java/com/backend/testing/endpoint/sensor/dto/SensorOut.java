package com.backend.testing.endpoint.sensor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorOut {
  private String sensorId;
  private String position;
  private String sensorType;
  private String description;
  private String comments;
  private String status;
}
