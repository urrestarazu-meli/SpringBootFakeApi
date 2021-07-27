package com.photogram.fake.api.modules.exceptions;

import com.photogram.fake.api.modules.entities.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryExceptionTest {

    @Test
    void getCode() {
        RepositoryException exception = new RepositoryException("message", null);

        assertEquals(ErrorCode.REPOSITORY_EXCEPTION, exception.getCode());
    }
}