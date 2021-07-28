package com.photogram.fake.api.modules.services.impl;

import com.photogram.fake.api.modules.repositories.SessionRepository;
import com.photogram.fake.api.modules.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
Deafult session service
 *
 */
@Service
public class DefaultSessionService implements SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    /*
    Creates a new session token
     *
     * @param model a session service model
     * @return
     */
    public String generate(Model model) {
        return sessionRepository.generate(model.getUserId());
    }

    /*
    Validates a users token
     *
     * @param model  a session service model
     * @return true when is a valid token
     */
    public boolean validate(Model model) {
        return sessionRepository.validate(model.getToken());
    }
}
