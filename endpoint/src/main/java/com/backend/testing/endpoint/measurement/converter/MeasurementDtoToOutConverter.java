package com.backend.testing.endpoint.measurement.converter;

import com.backend.testing.endpoint.measurement.dto.MeasurementOut;
import com.backend.testing.service.measurement.dto.MeasurementDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MeasurementDtoToOutConverter implements Converter<MeasurementDto, MeasurementOut> {
  @Override
  public MeasurementOut convert(MeasurementDto source) {
    if (source == null) {
      return null;
    }

    var measurement = new MeasurementOut();
    measurement.setSensorId(source.getSensor() != null ? source.getSensor().getSensorId() : "");
    measurement.setPosition(source.getSensor() != null ? source.getSensor().getPosition() : "");
    measurement.setStamp(source.getStamp().toString());
    measurement.setYear(source.getStamp().getYear());
    measurement.setValueType(source.getValueType().name().toLowerCase());
    measurement.setValue(source.getValue());

    return measurement;
  }
}
