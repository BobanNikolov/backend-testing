package com.backend.testing.model.repository;

import com.backend.testing.model.domain.enums.Status;
import com.backend.testing.model.domain.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, UUID> {
  List<Sensor> findAllByStatusNotIn(List<Status> statuses);
}
