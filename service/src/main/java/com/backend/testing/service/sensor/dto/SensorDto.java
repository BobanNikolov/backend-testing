package com.backend.testing.service.sensor.dto;

import com.backend.testing.model.domain.enums.SensorType;
import com.backend.testing.model.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SensorDto {
  private String sensorId;
  private String position;
  private SensorType sensorType;
  private String description;
  private String comments;
  private Status status;
}
