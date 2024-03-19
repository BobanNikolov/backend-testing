package com.netcetera.backend.testing.service.sensor.converter;

import com.netcetera.backend.testing.model.domain.Sensor;
import com.netcetera.backend.testing.service.sensor.dto.SensorDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SensorToSensorDtoConverter implements Converter<Sensor, SensorDto> {
  @Override
  public SensorDto convert(Sensor source) {
    if (source == null) {
      return null;
    }
    var sensorDto = new SensorDto();

    sensorDto.setSensorId(source.getSensorId().toString());
    sensorDto.setPosition(source.getPosition());
    sensorDto.setSensorType(source.getSensorType());
    sensorDto.setDescription(source.getDescription());
    sensorDto.setComments(source.getComments());
    sensorDto.setStatus(source.getStatus());

    return sensorDto;
  }
}
