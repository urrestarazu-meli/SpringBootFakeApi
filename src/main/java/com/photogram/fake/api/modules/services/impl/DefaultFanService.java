package com.photogram.fake.api.modules.services.impl;

import com.photogram.fake.api.modules.entities.domain.Post;
import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.exceptions.BusinessException;
import com.photogram.fake.api.modules.repositories.FanRepository;
import com.photogram.fake.api.modules.repositories.UsersRepository;
import com.photogram.fake.api.modules.services.FanService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultFanService implements FanService {
    @Autowired
    private FanRepository fanRepository;

    @Autowired
    private UsersRepository usersRepository;

    public User add(long userId) {
        User user = usersRepository.get(userId);

        fanRepository.add(user);

        return user;
    }

    @Override
    public List<User> get() {

        return fanRepository.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> getPostsFan(long userId) {

        Optional<User> fan = fanRepository.get(userId);

        if (!fan.isPresent()) {
            throw new BusinessException("couldn't find the fan.");
        }

        return usersRepository.getPosts(fan.get().getId());
    }
}
