package com.photogram.fake.api.modules.services;

import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Service;

/*
Session service
 *
 */
@Service
public interface SessionService {
    /*
        Creates a new session token
     *
     * @param model a session service model
     * @return
     */
    String generate(Model model);

    /*
        Validates a users token
    *
    * @param model  a session service model
    * @return true when is a valid token
    */
    boolean validate(Model model);

    /*
    Session service model
     *
     */
    @Builder
    @Getter
    class Model {
        private final long userId;
        private final String token;
    }
}
