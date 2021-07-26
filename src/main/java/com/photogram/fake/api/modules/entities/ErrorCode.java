package com.photogram.fake.api.modules.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  DEFAULT_ERROR_CODE(HttpStatus.INTERNAL_SERVER_ERROR),
  REPOSITORY_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR),
  BUSINESS_EXCEPTION(HttpStatus.I_AM_A_TEAPOT);

  private final HttpStatus httpStatus;
}
