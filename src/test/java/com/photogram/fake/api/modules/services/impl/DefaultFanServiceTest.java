package com.photogram.fake.api.modules.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.photogram.fake.api.modules.entities.domain.Post;
import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.services.FanService;
import com.photogram.fake.api.modules.usecase.AddFan;
import com.photogram.fake.api.modules.usecase.GetMyFans;
import com.photogram.fake.api.modules.usecase.GetsPostsFan;
import com.photogram.fake.api.modules.usecase.ValidateSession;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class DefaultFanServiceTest {
    private GetMyFans getMyFans = mock(GetMyFans.class);
    private GetsPostsFan getsPostsFan = mock(GetsPostsFan.class);
    private AddFan addFan = mock(AddFan.class);
    private ValidateSession validateSession = mock(ValidateSession.class);

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

        DefaultFanService fanService = new DefaultFanService(getMyFans, getsPostsFan, addFan, validateSession);
        User result = fanService.add(FanService.Model.builder()
                .userId(userId)
                .token("token")
                .build());

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

        DefaultFanService fanService = new DefaultFanService(getMyFans, getsPostsFan, addFan, validateSession);
        List<User> result = fanService.get(FanService.Model.builder()
                .userId(userId)
                .token("token")
                .build());

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

        DefaultFanService fanService = new DefaultFanService(getMyFans, getsPostsFan, addFan, validateSession);
        List<Post> result = fanService.getPostsFan(FanService.Model.builder()
                .userId(userId)
                .build());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(userId, result.get(0).getUserId());
    }
}