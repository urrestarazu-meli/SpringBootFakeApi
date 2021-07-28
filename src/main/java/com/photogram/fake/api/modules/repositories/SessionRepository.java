package com.photogram.fake.api.modules.repositories;

import com.photogram.fake.api.modules.stereotypes.Repository;
import java.util.UUID;
import lombok.Getter;

@Repository
public class SessionRepository {
    @Getter
    private String token;

    public String generate(long userId) {
        token = UUID.randomUUID().toString();
        return token;
    }

    public boolean validate(String sessionToken) {
        return token != null && token.equals(sessionToken);
    }
}
