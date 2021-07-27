package com.photogram.fake.api.modules.usecase.impl;

import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.repositories.FanRepository;
import com.photogram.fake.api.modules.repositories.UsersRepository;
import com.photogram.fake.api.modules.stereotypes.Usecase;
import com.photogram.fake.api.modules.usecase.AddFan;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Usecase
@AllArgsConstructor
public class DefaultAddFan implements AddFan {
    @Autowired
    private FanRepository fanRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public User add(Model model) {
        User user = usersRepository.get(model.getUserId());

        fanRepository.add(user);

        return user;
    }
}
