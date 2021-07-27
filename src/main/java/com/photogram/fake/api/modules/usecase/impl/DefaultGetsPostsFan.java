package com.photogram.fake.api.modules.usecase.impl;

import com.photogram.fake.api.modules.entities.domain.Post;
import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.exceptions.BusinessException;
import com.photogram.fake.api.modules.repositories.FanRepository;
import com.photogram.fake.api.modules.repositories.UsersRepository;
import com.photogram.fake.api.modules.stereotypes.Usecase;
import com.photogram.fake.api.modules.usecase.GetsPostsFan;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Usecase
@AllArgsConstructor
public class DefaultGetsPostsFan implements GetsPostsFan {
    @Autowired
    private FanRepository fanRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Post> get(Model model) {
        Optional<User> fan = fanRepository.get(model.getUserId());

        if (!fan.isPresent()) {
            throw new BusinessException("couldn't find the fan.");
        }

        return usersRepository.getPosts(fan.get().getId());
    }
}
