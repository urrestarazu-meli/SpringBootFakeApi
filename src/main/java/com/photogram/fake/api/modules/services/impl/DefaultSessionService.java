package com.photogram.fake.api.modules.services.impl;

import com.photogram.fake.api.modules.repositories.SessionRepository;
import com.photogram.fake.api.modules.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultSessionService implements SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    public String generate(long userId) {
        return sessionRepository.generate(userId);
    }

    public boolean validate(String sessionToken) {
        return sessionRepository.validate(sessionToken);
    }
}
