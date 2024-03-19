package com.netcetera.backend.testing.service.sensor.impl;

import com.netcetera.backend.testing.model.domain.Sensor;
import com.netcetera.backend.testing.model.domain.enums.SensorType;
import com.netcetera.backend.testing.model.domain.enums.Status;
import com.netcetera.backend.testing.model.repository.SensorRepository;
import com.netcetera.backend.testing.service.sensor.SensorService;
import com.netcetera.backend.testing.service.sensor.dto.SensorDto;
import com.netcetera.backend.testing.service.sensor.dto.SensorPersistCommand;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.netcetera.backend.testing.model.domain.enums.Status.REQUESTED;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {
  private final SensorRepository repository;
  private final Validator validator;
  private final ConversionService conversionService;

  @Override
  public SensorDto register(SensorPersistCommand sensor) {
    LOGGER.debug("register sensor - START - sensor: {}", sensor);
    final var constrainViolations = validator.validate(sensor);
    if (!constrainViolations.isEmpty()) {
      throw new ConstraintViolationException("Sensor(REGISTER) failed validation!", constrainViolations);
    }
    final var sensorToRegister = convert(sensor);
    LOGGER.debug("converter sensor to register - START - sensorToRegister: {}", sensorToRegister);
    final var result = repository.saveAndFlush(sensorToRegister);
    final var convertedResult = conversionService.convert(result, SensorDto.class);
    LOGGER.debug("register sensor - END - result: {}", convertedResult);
    return convertedResult;
  }

  @Override
  public List<SensorDto> listAvailable() {
    LOGGER.debug("list available sensors - BEGIN");
    final var result = this.repository.findAllByStatusNotIn(List.of(REQUESTED));
    final var convertedResult = result.stream()
        .map(it -> conversionService.convert(it, SensorDto.class))
        .filter(Objects::nonNull)
        .toList();
    LOGGER.debug("register sensor - END - result: {}", convertedResult);
    return convertedResult;
  }

  private Sensor convert(SensorPersistCommand sensorPersistCommand) {
    if (sensorPersistCommand == null) {
      return null;
    }
    var sensor = new Sensor();

    sensor.setPosition(sensorPersistCommand.getPosition());
    sensor.setSensorType(SensorType.fromValue(sensorPersistCommand.getSensorType()));
    sensor.setDescription(sensorPersistCommand.getDescription());
    sensor.setComments(sensorPersistCommand.getComments());
    sensor.setStatus(Status.valueOf(sensorPersistCommand.getStatus()));

    return sensor;
  }
}
