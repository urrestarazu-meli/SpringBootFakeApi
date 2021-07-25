package com.photogram.fake.api.modules.exceptions;

import com.photogram.fake.api.modules.entities.ErrorCode;

public class BusinessException extends ApplicationException {
    public BusinessException(String message) {
        super(ErrorCode.BUSINESS_EXCEPTION, message, null);
    }
}
