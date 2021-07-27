package com.photogram.fake.api.modules.exceptions;

import com.photogram.fake.api.modules.entities.ErrorCode;

/*
 * Repository Exception template
 */
public class RepositoryException extends ApplicationException {
    public RepositoryException(String message, Throwable cause) {
        super(ErrorCode.REPOSITORY_EXCEPTION, message, cause);
    }
}
