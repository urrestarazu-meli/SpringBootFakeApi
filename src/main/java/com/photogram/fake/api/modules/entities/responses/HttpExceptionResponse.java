package com.photogram.fake.api.modules.entities.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpExceptionResponse {
    private final String code;
    private final String message;
}
