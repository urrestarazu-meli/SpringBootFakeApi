package com.photogram.fake.api.modules.exceptions.handler;

import com.photogram.fake.api.modules.entities.ErrorCode;
import com.photogram.fake.api.modules.entities.responses.HttpExceptionResponse;
import com.photogram.fake.api.modules.exceptions.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/*
 Handle the exceptions globally
 */
@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(value = {ApplicationException.class})
    protected ResponseEntity<HttpExceptionResponse> handleException(
            final ApplicationException appException,
            final WebRequest request) {

        ErrorCode errorCode = ErrorCode.DEFAULT_ERROR_CODE;
        if (appException.getCode() != null) {
            errorCode = appException.getCode();
        }

        log.error("[exception-handler: stack-trace] ", appException);

        HttpExceptionResponse responseExc = HttpExceptionResponse.builder()
                .code(errorCode.name())
                .message(appException.getMessage())
                .build();

        return ResponseEntity.status(errorCode.getHttpStatus()).body(responseExc);
    }
}
