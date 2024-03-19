package com.backend.testing.service.measurement;

import com.backend.testing.model.domain.Measurement;
import com.backend.testing.model.domain.Sensor;
import com.backend.testing.model.repository.MeasurementRepository;
import com.backend.testing.model.repository.SensorRepository;
import com.backend.testing.service.measurement.dto.MeasurementDto;
import com.backend.testing.service.measurement.dto.MeasurementPersistCommand;
import com.backend.testing.service.measurement.impl.MeasurementServiceImpl;
import com.backend.testing.service.sensor.dto.SensorDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.ConversionService;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static com.backend.testing.model.domain.enums.SensorType.MOEPP;
import static com.backend.testing.model.domain.enums.Status.ACTIVE;
import static com.backend.testing.model.domain.enums.ValueType.PM10;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MeasurementServiceTest {
  private MeasurementRepository repository;
  private SensorRepository sensorRepository;
  private Validator validator;
  private ConversionService conversionService;
  private MeasurementService instanceUnderTest;

  @BeforeEach
  void init() {
    repository = mock(MeasurementRepository.class);
    sensorRepository = mock(SensorRepository.class);
    validator = mock(Validator.class);
    conversionService = mock(ConversionService.class);
    instanceUnderTest = new MeasurementServiceImpl(repository, sensorRepository, validator, conversionService);
  }

  @Test
  void givenValidMeasurementPersistCommand_whenAdd_thenSuccess() {
    final var validMeasurementPersistCommand = generateMeasurementPersistCommand();
    final var sensor = generateSensor();

    when(validator.validate(any(MeasurementPersistCommand.class))).thenReturn(new HashSet<>());
    when(sensorRepository.findById(any(UUID.class))).thenReturn(Optional.of(sensor));
    when(repository.saveAndFlush(any(Measurement.class))).thenReturn(generateMeasurement());
    when(conversionService.convert(any(Measurement.class), eq(MeasurementDto.class))).thenReturn(generateMeasurementDto());

    final var result = this.instanceUnderTest.add(validMeasurementPersistCommand);
    assertNotNull(result);
    verify(validator).validate(any(MeasurementPersistCommand.class));
  }

  @Test
  void givenInValidMeasurementPersistCommand_whenAdd_thenFailWithConstraintViolations() {
    final var validMeasurementPersistCommand = generateMeasurementPersistCommand();

    var violation = mock(ConstraintViolation.class);
    var constraintViolations = new HashSet<ConstraintViolation<MeasurementPersistCommand>>();
    constraintViolations.add(violation);

    when(validator.validate(any(MeasurementPersistCommand.class))).thenReturn(constraintViolations);

    assertThrows(ConstraintViolationException.class, () -> {
      this.instanceUnderTest.add(validMeasurementPersistCommand);
    });
  }

  @Test
  void givenInvalidMeasurementPersistCommand_whenAdd_thenFailWithEntityNotFoundForSensorId() {
    final var validMeasurementPersistCommand = generateMeasurementPersistCommand();

    when(validator.validate(any(MeasurementPersistCommand.class))).thenReturn(new HashSet<>());
    when(sensorRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> {
      this.instanceUnderTest.add(validMeasurementPersistCommand);
    });
  }

  private static MeasurementPersistCommand generateMeasurementPersistCommand() {
    var measurementPersistCommand = new MeasurementPersistCommand();

    measurementPersistCommand.setSensorId(randomUUID().toString());
    measurementPersistCommand.setStamp(OffsetDateTime.now());
    measurementPersistCommand.setValueType("pm10");
    measurementPersistCommand.setValue("100");

    return measurementPersistCommand;
  }

  private static MeasurementDto generateMeasurementDto() {
    var measurementDto = new MeasurementDto();

    measurementDto.setMeasurementId(randomUUID().toString());
    measurementDto.setSensor(generateSensorDto());
    measurementDto.setStamp(OffsetDateTime.now());
    measurementDto.setValueType(PM10);
    measurementDto.setValue("100");

    return measurementDto;
  }

  private static Measurement generateMeasurement() {
    var measurementDto = new Measurement();

    measurementDto.setMeasurementId(randomUUID());
    measurementDto.setSensor(generateSensor());
    measurementDto.setStamp(OffsetDateTime.now());
    measurementDto.setValueType(PM10);
    measurementDto.setValue("100");

    return measurementDto;
  }

  private static Sensor generateSensor() {
    var sensor = new Sensor();

    sensor.setSensorId(randomUUID());
    sensor.setPosition(randomUUID().toString());
    sensor.setSensorType(MOEPP);
    sensor.setDescription(randomUUID().toString());
    sensor.setComments(randomUUID().toString());
    sensor.setStatus(ACTIVE);

    return sensor;
  }

  private static SensorDto generateSensorDto() {
    var sensor = new SensorDto();

    sensor.setSensorId(randomUUID().toString());
    sensor.setPosition(randomUUID().toString());
    sensor.setSensorType(MOEPP);
    sensor.setDescription(randomUUID().toString());
    sensor.setComments(randomUUID().toString());
    sensor.setStatus(ACTIVE);

    return sensor;
  }
}