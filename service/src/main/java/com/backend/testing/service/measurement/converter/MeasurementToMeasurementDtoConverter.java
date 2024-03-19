package com.backend.testing.service.measurement.converter;

import com.backend.testing.model.domain.Measurement;
import com.backend.testing.service.measurement.dto.MeasurementDto;
import com.backend.testing.service.sensor.converter.SensorToSensorDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeasurementToMeasurementDtoConverter implements Converter<Measurement, MeasurementDto> {

  private final SensorToSensorDtoConverter sensorToSensorDtoConverter;

  @Override
  public MeasurementDto convert(Measurement source) {
    if (source == null) {
      return null;
    }
    var measurement = new MeasurementDto();

    measurement.setMeasurementId(source.getMeasurementId().toString());
    measurement.setSensor(sensorToSensorDtoConverter.convert(source.getSensor()));
    measurement.setStamp(source.getStamp());
    measurement.setValueType(source.getValueType());
    measurement.setValue(source.getValue());

    return measurement;
  }
}
