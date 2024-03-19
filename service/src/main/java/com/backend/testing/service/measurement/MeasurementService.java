package com.backend.testing.service.measurement;

import com.backend.testing.service.measurement.dto.MeasurementDto;
import com.backend.testing.service.measurement.dto.MeasurementPersistCommand;

import java.time.OffsetDateTime;
import java.util.List;

public interface MeasurementService {
  MeasurementDto add(MeasurementPersistCommand measurement);

  List<MeasurementDto> listAllFiltered(String sensorId, OffsetDateTime fromDateTime, OffsetDateTime toDateTime);
}
