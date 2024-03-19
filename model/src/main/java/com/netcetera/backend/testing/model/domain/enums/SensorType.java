package com.netcetera.backend.testing.model.domain.enums;

import java.util.Arrays;
import java.util.List;

public enum SensorType {
  MOEPP("0"),
  SKOPJE_PULSE_LORAWAN("1"),
  SKOPJE_PULSE_WIFI("2"),
  PULSE_ECO_WIFI("3"),
  PULSE_ECO_LORAWAN("4"),
  PENGY_DEVICE("20001"),
  URAD("20002"),
  AIR_THINGS("20003"),
  SENSOR_COMMUNITY("20004");


  public final String value;
  SensorType(String value) {
    this.value = value;
  }
  public static List<String> getAllValues() {
    return Arrays.stream(SensorType.values())
        .map(sensorType -> sensorType.value)
        .toList();
  }

  public static SensorType fromValue(String value) {
    return Arrays.stream(SensorType.values())
        .filter(sensorType -> sensorType.value.equals(value))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
  }

  public static String getValue(SensorType sensorType) {
    return sensorType.value;
  }

}
