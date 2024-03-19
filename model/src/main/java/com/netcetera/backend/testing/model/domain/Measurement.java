package com.netcetera.backend.testing.model.domain;

import com.netcetera.backend.testing.model.domain.enums.Status;
import com.netcetera.backend.testing.model.domain.enums.ValueType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "measurement", schema = "netcetera_testing")
public class Measurement {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "measurement_id")
  private UUID measurementId;

  @OneToOne
  @JoinColumn(name = "sensor_id")
  private Sensor sensor;

  @Column(name = "stamp", nullable = false)
  private OffsetDateTime stamp;

  @Column(name = "value_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private ValueType valueType;

  @Column(name = "value", nullable = false)
  private String value;
}
