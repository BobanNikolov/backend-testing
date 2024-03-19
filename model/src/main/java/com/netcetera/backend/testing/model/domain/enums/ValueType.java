package com.netcetera.backend.testing.model.domain.enums;

import java.util.Arrays;
import java.util.List;

public enum ValueType {
  PM10, PM25, TEMPERATURE, HUMIDITY, NOISE;

  public static List<String> getAllValues() {
    return Arrays.stream(ValueType.values())
        .map(Enum::name)
        .toList();
  }
}
