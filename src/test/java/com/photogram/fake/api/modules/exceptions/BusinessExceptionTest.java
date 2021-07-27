package com.photogram.fake.api.modules.exceptions;

import com.photogram.fake.api.modules.entities.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessExceptionTest {

    @Test
    void getCode() {
        BusinessException exception = new BusinessException("message");
        assertEquals(ErrorCode.BUSINESS_EXCEPTION, exception.getCode());
    }
}