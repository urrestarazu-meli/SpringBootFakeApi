package com.photogram.fake.api.modules.repositories;

import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.stereotypes.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

/*
    Fans repository
 */
@Repository
@AllArgsConstructor
public class FanRepository {
    @Getter
    private List<User> fans;

    public FanRepository() {
        this.fans = new ArrayList<>();
    }

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
