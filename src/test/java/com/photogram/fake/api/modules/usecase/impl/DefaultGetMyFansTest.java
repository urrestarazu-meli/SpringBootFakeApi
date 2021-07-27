package com.photogram.fake.api.modules.usecase.impl;

import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.repositories.FanRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultGetMyFansTest {
    private FanRepository fanRepository = mock(FanRepository.class);

    @Test
    void get() {
        User user = User.builder()
                .id(1L)
                .name("Peter")
                .build();
        when(fanRepository.get())
                .thenReturn(Collections.singletonList(user));

        DefaultGetMyFans getMyFans = new DefaultGetMyFans(fanRepository);
        List<User> result = getMyFans.get();

        assertNotNull(result);
        assertEquals(Collections.singletonList(user), result);
    }
}