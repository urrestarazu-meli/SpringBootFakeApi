package com.photogram.fake.api.modules.repositories;

import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.stereotypes.Repository;
import java.util.UUID;
import lombok.Getter;

@Repository
public class SessionRepository {
    @Getter
    private String token;

    /**
     Simulates the obtaining of user untoken
     *
     * @param userId a user id
     * @return a token
     */
    public String generate(long userId) {
        token = UUID.randomUUID().toString();
        return token;
    }

    /*
        Validates the user's token
     */
    public boolean validate(String sessionToken) {
        return token != null && token.equals(sessionToken);
    }

    /*
    simulates the user’s acquisition of the current session

     *
     * @param token a user's session token
     * @return the user
     */
    public User getUser(String token) {
        return User.builder()
                .id(1982L)
                .email("gatman@gmail.com")
                .name("Bruce Wayne")
                .username("batman")
                .build();
    }
}
