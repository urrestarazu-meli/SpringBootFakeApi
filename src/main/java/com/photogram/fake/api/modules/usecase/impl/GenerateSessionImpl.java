package com.photogram.fake.api.modules.usecase.impl;

import com.photogram.fake.api.modules.repositories.SessionRepository;
import com.photogram.fake.api.modules.stereotypes.Usecase;
import com.photogram.fake.api.modules.usecase.GenerateSession;
import org.springframework.beans.factory.annotation.Autowired;

@Usecase
public class GenerateSessionImpl implements GenerateSession {
    @Autowired
    private SessionRepository sessionRepository;

    public String generate(long userId) {
        return sessionRepository.generate(userId);
    }
}
