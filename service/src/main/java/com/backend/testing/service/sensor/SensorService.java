package com.backend.testing.service.sensor;

import com.backend.testing.service.sensor.dto.SensorPersistCommand;
import com.backend.testing.service.sensor.dto.SensorDto;

import java.util.List;

public interface SensorService {
  SensorDto register(SensorPersistCommand sensor);

  List<SensorDto> listAvailable();
}
