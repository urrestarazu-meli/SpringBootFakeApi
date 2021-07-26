package com.photogram.fake.api.modules.exceptions;

import com.photogram.fake.api.modules.entities.ErrorCode;
import lombok.Getter;

/*
    Application Exception template
 */
@Getter
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final ErrorCode code;

    public ApplicationException(final ErrorCode code, final String message, final Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
