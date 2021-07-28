package com.photogram.fake.api.modules.usecase.impl;

import com.photogram.fake.api.modules.exceptions.BusinessException;
import com.photogram.fake.api.modules.exceptions.SessionException;
import com.photogram.fake.api.modules.repositories.SessionRepository;
import com.photogram.fake.api.modules.stereotypes.Usecase;
import com.photogram.fake.api.modules.usecase.ValidateSession;
import org.springframework.beans.factory.annotation.Autowired;

@Usecase
public class ValidateSessionImpl implements ValidateSession {
    @Autowired
    private SessionRepository sessionRepository;

    public void validate(Model model) {
        boolean validation = sessionRepository.validate(model.getSessionToken());

        if (!validation) {
            throw new SessionException("Invalid session token.");
        }
    }
}
