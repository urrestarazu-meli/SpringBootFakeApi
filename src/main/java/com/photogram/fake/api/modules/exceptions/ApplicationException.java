package com.photogram.fake.api.modules.exceptions;

import com.photogram.fake.api.modules.entities.ErrorCode;
import lombok.Getter;

/**
 *
 */
@Getter
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private ErrorCode code;

    public ApplicationException(final ErrorCode code, final String message, final Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
