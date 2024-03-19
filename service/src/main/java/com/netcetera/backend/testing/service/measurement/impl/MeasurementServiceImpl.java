package com.netcetera.backend.testing.service.measurement.impl;

import com.netcetera.backend.testing.model.domain.Measurement;
import com.netcetera.backend.testing.model.domain.Sensor;
import com.netcetera.backend.testing.model.domain.enums.ValueType;
import com.netcetera.backend.testing.model.repository.MeasurementRepository;
import com.netcetera.backend.testing.service.measurement.MeasurementService;
import com.netcetera.backend.testing.service.measurement.dto.MeasurementDto;
import com.netcetera.backend.testing.service.measurement.dto.MeasurementPersistCommand;
import com.netcetera.backend.testing.service.sensor.dto.SensorDto;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.netcetera.backend.testing.model.domain.enums.Status.REQUESTED;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {
  private final MeasurementRepository repository;
  private final Validator validator;
  private final ConversionService conversionService;

  @Override
  public MeasurementDto add(MeasurementPersistCommand measurement) {
    LOGGER.debug("add measurement - START - measurement: {}", measurement);
    final var constrainViolations = validator.validate(measurement);
    if (!constrainViolations.isEmpty()) {
      throw new ConstraintViolationException("Measurement(ADD) failed validation!", constrainViolations);
    }
    final var measurementToAdd = convert(measurement);
    LOGGER.debug("converter measurement to add - START - measurementToAdd: {}", measurementToAdd);
    final var result = repository.saveAndFlush(measurementToAdd);
    final var convertedResult = conversionService.convert(result, MeasurementDto.class);
    LOGGER.debug("add measurement - END - result: {}", convertedResult);
    return convertedResult;
  }

  @Override
  public List<MeasurementDto> listAllFiltered(String sensorId, OffsetDateTime fromDateTime, OffsetDateTime toDateTime) {
    LOGGER.debug("list filtered measurements - BEGIN - sensorId : {}, fromDateTime : {}, toDateTime : {}",
        sensorId, fromDateTime, toDateTime);
    final var result = this.repository.findAllBySensorSensorIdAndStampBetween(UUID.fromString(sensorId), fromDateTime, toDateTime);
    final var convertedResult = result.stream()
        .map(it -> conversionService.convert(it, MeasurementDto.class))
        .filter(Objects::nonNull)
        .toList();
    LOGGER.debug("list filtered measurements - END - result: {}", convertedResult);
    return convertedResult;
  }

  private Measurement convert(MeasurementPersistCommand measurementPersistCommand) {
    if (measurementPersistCommand == null) {
      return null;
    }
    var measurement = new Measurement();

    measurement.setSensor(Sensor.builder().sensorId(UUID.fromString(measurementPersistCommand.getSensorId())).build());
    measurement.setStamp(measurementPersistCommand.getStamp());
    measurement.setValueType(ValueType.valueOf(measurementPersistCommand.getValueType().toUpperCase()));
    measurement.setValue(measurementPersistCommand.getValue());

    return measurement;
  }
}
