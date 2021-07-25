package com.photogram.fake.api.modules.services;

import com.photogram.fake.api.modules.entities.domain.Post;
import com.photogram.fake.api.modules.entities.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FanService {
    User add(long userId);

    List<User> get();

    List<Post> getPostsFan(long userId);
}
