package com.netcetera.backend.testing.model.repository;

import com.netcetera.backend.testing.model.domain.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, UUID> {
  List<Measurement> findAllBySensorSensorIdAndStampBetween(UUID sensorId, OffsetDateTime fromDateTime, OffsetDateTime toDateTime);
}
