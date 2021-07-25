package com.photogram.fake.api.modules.repositories;

import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.exceptions.BusinessException;
import com.photogram.fake.api.modules.stereotypes.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FanRepository {
    static List<User> fans = new ArrayList<>();

    public void add(User user) {
        fans.add(user);
    }

    public List<User> get() {
        return fans;
    }

    public User get(long userId) {
        Optional<User> fan = fans.stream()
                .filter(f -> f.getId().equals(userId))
                .findFirst();

        if (!fan.isPresent()) {
            throw new BusinessException("couldn't find the fan.");
        }

        return fan.get();
    }
}
