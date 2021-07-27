package com.photogram.fake.api.modules.usecase.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.repositories.FanRepository;
import com.photogram.fake.api.modules.repositories.UsersRepository;
import com.photogram.fake.api.modules.usecase.AddFan;
import org.junit.jupiter.api.Test;

class DefaultAddFanTest {
    private FanRepository fanRepository = mock(FanRepository.class);
    private UsersRepository usersRepository = mock(UsersRepository.class);
    ;

    @Test
    void add() {
        long userId = 999L;
        User user = User.builder().id(userId)
                .build();

        when(usersRepository.get(userId))
                .thenReturn(user);

        DefaultAddFan addFan = new DefaultAddFan(fanRepository, usersRepository);
        User result = addFan.add(AddFan.Model.builder()
                .userId(userId)
                .build());

        assertNotNull(result);
    }
}