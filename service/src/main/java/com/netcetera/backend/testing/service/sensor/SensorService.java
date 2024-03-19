package com.netcetera.backend.testing.service.sensor;

import com.netcetera.backend.testing.service.sensor.dto.SensorDto;
import com.netcetera.backend.testing.service.sensor.dto.SensorPersistCommand;

import java.util.List;

public interface SensorService {
  SensorDto register(SensorPersistCommand sensor);

  List<SensorDto> listAvailable();
}
