package com.photogram.fake.api.modules.services;

import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public interface SessionService {
    /**
     *
     * @param userId
     * @return
     */
    String generate(long userId);

    /**
     *
     * @param sessionToken
     * @return
     */
    boolean validate(String sessionToken);
}
