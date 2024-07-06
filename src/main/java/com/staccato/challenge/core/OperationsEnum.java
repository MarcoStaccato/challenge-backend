package com.staccato.challenge.core;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum OperationsEnum {
  CREATION("CREATION"),
  ADDITION("ADDITION"),
  SUBSTRACTION("SUBSTRACTION"),
  MULTIPLICATION("MULTIPLICATION"),
  DIVISION("DIVISION"),
  SQUARE_ROOT("SQUARE_ROOT"),
  RANDOM_STRING("RANDOM_STRING");

  private String value;

  private static final Map<String,OperationsEnum> ENUM_MAP;

  static {
    Map<String,OperationsEnum> map = new ConcurrentHashMap<String, OperationsEnum>();
    for (OperationsEnum instance : OperationsEnum.values()) {
      map.put(instance.getValue().toLowerCase(),instance);
    }
    ENUM_MAP = Collections.unmodifiableMap(map);
  }

  OperationsEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  public static OperationsEnum get(String name) {
    return ENUM_MAP.get(name.toLowerCase());
  }

}
