package com.netcetera.backend.testing.endpoint.sensor.controller;

import com.netcetera.backend.testing.endpoint.sensor.dto.SensorOut;
import com.netcetera.backend.testing.service.sensor.SensorService;
import com.netcetera.backend.testing.service.sensor.dto.SensorPersistCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/api/v1/sensor")
@RequiredArgsConstructor
public class SensorController {
  private final SensorService sensorService;
  private final ConversionService conversionService;

  @PostMapping("/register")
  public ResponseEntity<SensorOut> register(@RequestBody SensorPersistCommand sensor) {
    LOGGER.debug("register sensor - BEGIN - source: {}", sensor);
    final var result = this.sensorService.register(sensor);
    final var convertedResult = this.conversionService.convert(result, SensorOut.class);
    LOGGER.debug("register sensor - END - result: {}", convertedResult);
    return ResponseEntity.ok(convertedResult);
  }

  @GetMapping("/list-available")
  public ResponseEntity<List<SensorOut>> listAvailable() {
    LOGGER.debug("list available sensors - BEGIN");
    final var result = this.sensorService.listAvailable();
    final var convertedResult = result.stream()
        .map(it -> conversionService.convert(it, SensorOut.class))
        .filter(Objects::nonNull)
        .toList();
    LOGGER.debug("register sensor - END - result: {}", convertedResult);
    return ResponseEntity.ok(convertedResult);
  }
}
