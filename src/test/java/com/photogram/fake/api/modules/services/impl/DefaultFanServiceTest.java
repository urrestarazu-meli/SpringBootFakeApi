package com.photogram.fake.api.modules.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.photogram.fake.api.modules.entities.domain.Post;
import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.usecase.AddFan;
import com.photogram.fake.api.modules.usecase.GetMyFans;
import com.photogram.fake.api.modules.usecase.GetsPostsFan;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class DefaultFanServiceTest {
    private GetMyFans getMyFans = mock(GetMyFans.class);
    private GetsPostsFan getsPostsFan = mock(GetsPostsFan.class);
    private AddFan addFan = mock(AddFan.class);

    @Test
    void add() {
        Long userId = 999L;
        User user = User.builder()
                .id(userId)
                .name("James Brown")
                .email("feel@good.com")
                .build();
        when(addFan.add(any()))
                .thenReturn(user);

        DefaultFanService fanService = new DefaultFanService(getMyFans, getsPostsFan, addFan);
        User result = fanService.add(userId);

        assertEquals(user, result);
    }

    @Test
    void get() {
        Long userId = 999L;
        User user = User.builder()
                .id(userId)
                .name("James Brown")
                .email("feel@good.com")
                .build();
        when(addFan.add(any()))
                .thenReturn(user);

        when(getMyFans.get())
                .thenReturn(Collections.singletonList(user));

        DefaultFanService fanService = new DefaultFanService(getMyFans, getsPostsFan, addFan);
        List<User> result = fanService.get();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(userId, result.get(0).getId());
    }

    @Test
    void getPostsFan() {
        long userId = 999L;
        Post post = Post.builder()
                .id(1L)
                .userId(userId)
                .build();

        when(getsPostsFan.get(any()))
                .thenReturn(Collections.singletonList(post));

        DefaultFanService fanService = new DefaultFanService(getMyFans, getsPostsFan, addFan);
        List<Post> result = fanService.getPostsFan(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(userId, result.get(0).getUserId());
    }
}