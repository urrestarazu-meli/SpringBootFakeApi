package com.photogram.fake.api.modules.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  DEFAULT_ERROR_CODE(HttpStatus.INTERNAL_SERVER_ERROR),
  NO_SOLUTION_FOUND(HttpStatus.CONFLICT),
  EXTERNAL_COMMUNICATION(HttpStatus.INTERNAL_SERVER_ERROR);

  private final HttpStatus httpStatus;
}
