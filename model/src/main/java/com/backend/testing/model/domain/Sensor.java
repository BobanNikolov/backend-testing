package com.backend.testing.model.domain;

import com.backend.testing.model.domain.enums.Status;
import com.backend.testing.model.domain.enums.SensorType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "sensor", schema = "backend_testing")
public class Sensor {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "sensor_id")
  private UUID sensorId;
  @Column(name = "position", nullable = false)
  private String position;
  @Column(name = "sensor_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private SensorType sensorType;
  @Column(name = "description", nullable = false)
  private String description;
  @Column(name = "comments", nullable = false)
  private String comments;
  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private Status status;
}
