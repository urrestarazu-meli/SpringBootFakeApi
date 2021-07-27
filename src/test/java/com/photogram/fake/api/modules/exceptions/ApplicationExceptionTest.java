package com.photogram.fake.api.modules.exceptions;

import com.photogram.fake.api.modules.entities.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationExceptionTest {

    @Test
    void getCode() {
        ApplicationException exception = new ApplicationException(ErrorCode.DEFAULT_ERROR_CODE, "message", null);

        assertEquals(ErrorCode.DEFAULT_ERROR_CODE, exception.getCode());
    }
}