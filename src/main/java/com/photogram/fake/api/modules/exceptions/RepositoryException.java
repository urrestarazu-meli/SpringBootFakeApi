package com.photogram.fake.api.modules.exceptions;

import com.photogram.fake.api.modules.entities.ErrorCode;

/**
 *
 */
public class RepositoryException extends ApplicationException {
    public RepositoryException(String message, Throwable cause) {
        super(ErrorCode.REPOSITORY_EXCEPTION, message, cause);
    }
}
