package com.photogram.fake.api.modules.exceptions;

import com.photogram.fake.api.modules.entities.ErrorCode;

/*
 Session Exception template
 */
public class SessionException extends ApplicationException {
    public SessionException(String message) {
        super(ErrorCode.SESSION_EXCEPTION, message, null);
    }
}
