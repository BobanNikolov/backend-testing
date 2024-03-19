package com.backend.testing.endpoint.sensor.converter;

import com.backend.testing.endpoint.sensor.dto.SensorOut;
import com.backend.testing.model.domain.enums.SensorType;
import com.backend.testing.service.sensor.dto.SensorDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SensorDtoToOutConverter implements Converter<SensorDto, SensorOut> {
  @Override
  public SensorOut convert(SensorDto source) {
    if (source == null) {
      return null;
    }
    var sensor = new SensorOut();

    sensor.setSensorId(source.getSensorId());
    sensor.setPosition(source.getPosition());
    sensor.setSensorType(SensorType.getValue(source.getSensorType()));
    sensor.setDescription(source.getDescription());
    sensor.setComments(source.getComments());
    sensor.setStatus(source.getStatus().name());

    return sensor;
  }
}
