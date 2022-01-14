package com.br.sicredi.adapter.dto;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum StatusEnum {
  A, I, B, P;

  public static boolean contains(String name) {
    return Arrays.stream(values()).anyMatch(statusEnum -> statusEnum.name().equals(name));
  }

  public static boolean notContains(String name) {
    return !contains(name);
  }
}
