package com.photogram.fake.api.modules.repositories;

import static org.junit.jupiter.api.Assertions.*;

import com.photogram.fake.api.modules.entities.domain.User;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class FanRepositoryTest {

    @Test
    void add() {
        FanRepository fanRepository = new FanRepository();
        User user = User.builder()
                .id(999L)
                .build();
        fanRepository.add(user);

        assertEquals(Collections.singletonList(user), fanRepository.getFans());
    }

    @Test
    void getAll() {
        FanRepository fanRepository = new FanRepository();
        List<User> result = fanRepository.get();
        assertTrue(result.isEmpty());
    }

    @Test
    void getFan() {
        FanRepository fanRepository = new FanRepository();
        Long userId = 999L;
        User user = User.builder().id(userId).build();
        fanRepository.add(user);

        Optional<User> result = fanRepository.get(userId);
        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getId());
    }
}