package com.jonybuzz.encontralo.controller.error;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiError {

  private final String exception;
  private final String message;

}