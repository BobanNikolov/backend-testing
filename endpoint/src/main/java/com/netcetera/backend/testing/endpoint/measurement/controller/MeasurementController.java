package com.netcetera.backend.testing.endpoint.measurement.controller;

import com.netcetera.backend.testing.endpoint.measurement.dto.MeasurementOut;
import com.netcetera.backend.testing.service.measurement.MeasurementService;
import com.netcetera.backend.testing.service.measurement.dto.MeasurementPersistCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/api/v1/measurement")
@RequiredArgsConstructor
public class MeasurementController {
  private final MeasurementService measurementService;
  private final ConversionService conversionService;

  @PostMapping("/add")
  public ResponseEntity<MeasurementOut> add(@RequestBody MeasurementPersistCommand measurement) {
    LOGGER.debug("add measurement - BEGIN - source: {}", measurement);
    final var result = this.measurementService.add(measurement);
    final var convertedResult = this.conversionService.convert(result, MeasurementOut.class);
    LOGGER.debug("add measurement - END - result: {}", convertedResult);
    return ResponseEntity.ok(convertedResult);
  }

  @GetMapping("/list-all-filtered")
  public ResponseEntity<List<MeasurementOut>> listAllFiltered(@RequestParam("sensorId") String sensorId,
                                                              @RequestParam("fromDateTime") OffsetDateTime fromDateTime,
                                                              @RequestParam("toDateTime") OffsetDateTime toDateTime) {
    LOGGER.debug("list filtered measurements - BEGIN");
    final var result = this.measurementService.listAllFiltered(sensorId, fromDateTime, toDateTime);
    final var convertedResult = result.stream()
        .map(it -> conversionService.convert(it, MeasurementOut.class))
        .filter(Objects::nonNull)
        .toList();
    LOGGER.debug("list filtered measurements - END - result: {}", convertedResult);
    return ResponseEntity.ok(convertedResult);
  }
}
