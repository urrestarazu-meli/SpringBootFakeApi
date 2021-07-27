package com.photogram.fake.api.modules.usecase.impl;

import com.photogram.fake.api.modules.entities.domain.Post;
import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.repositories.FanRepository;
import com.photogram.fake.api.modules.repositories.UsersRepository;
import com.photogram.fake.api.modules.usecase.GetsPostsFan;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultGetsPostsFanTest {
    private FanRepository fanRepository = mock(FanRepository.class);
    private UsersRepository usersRepository = mock(UsersRepository.class);

    @Test
    void get() {
        long userId = 999L;
        User user = User.builder()
                .id(userId)
                .name("Peter")
                .build();

        Post post = Post.builder()
                .id(1L)
                .userId(userId)
                .build();

        when(fanRepository.get(userId))
                .thenReturn(Optional.of(user));

        when(usersRepository.getPosts(userId))
                .thenReturn(Collections.singletonList(post));

        DefaultGetsPostsFan getsPostsFan = new DefaultGetsPostsFan(fanRepository, usersRepository);
        List<Post> result = getsPostsFan.get(GetsPostsFan.Model.builder()
                .userId(userId)
                .build());

        assertNotNull(result);
        assertEquals(Collections.singletonList(post), result);
    }
}