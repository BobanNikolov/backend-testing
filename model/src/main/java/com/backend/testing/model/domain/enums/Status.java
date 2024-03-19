package com.backend.testing.model.domain.enums;

import java.util.Arrays;
import java.util.List;

public enum Status {
  REQUESTED, ACTIVE, ACTIVE_UNCONFIRMED, INACTIVE, NOT_CLAIMED, NOT_CLAIMED_UNCONFIRMED, BANNED;

  public static List<String> getAllValues() {
    return Arrays.stream(Status.values())
        .map(Enum::name)
        .toList();
    }
}
