package com.photogram.fake.api.modules.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.photogram.fake.api.modules.repositories.SessionRepository;
import com.photogram.fake.api.modules.services.SessionService;
import org.junit.jupiter.api.Test;

class DefaultSessionServiceTest {
    private SessionRepository sessionRepository = mock(SessionRepository.class);

    @Test
    void generate() {
        long userId = 999L;
        String token = "bb2e5081-826d-427b-92a5-604edb83aff0";
        when(sessionRepository.generate(userId))
                .thenReturn(token);

        DefaultSessionService defaultSessionService = new DefaultSessionService(sessionRepository);
        String response = defaultSessionService.generate(SessionService.Model
                .builder()
                .userId(userId)
                .build());

        assertEquals(token, response);
    }
}