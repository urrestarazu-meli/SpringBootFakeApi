package com.photogram.fake.api.modules.exceptions;

import com.photogram.fake.api.modules.entities.ErrorCode;

/*
 Business Exception template
 */
public class BusinessException extends ApplicationException {
    public BusinessException(String message) {
        super(ErrorCode.BUSINESS_EXCEPTION, message, null);
    }
}
