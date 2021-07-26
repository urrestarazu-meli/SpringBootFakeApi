package com.photogram.fake.api.modules.repositories;

import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.stereotypes.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
    Fans repository
 */
@Repository
public class FanRepository {
    static List<User> fans = new ArrayList<>();

    /*
      Add a fan
     * @param user user to add
     */
    public void add(User user) {
        fans.add(user);
    }

    /*
    obtain my fans

     * @return list of fans
     */
    public List<User> get() {
        return fans;
    }

    /*
    obtain a fan
     * @param userId a user Id
     * @return my fan, empty if a couldn't find it
     */
    public Optional<User> get(long userId) {
        return fans.stream()
                .filter(f -> f.getId().equals(userId))
                .findFirst();
    }
}
