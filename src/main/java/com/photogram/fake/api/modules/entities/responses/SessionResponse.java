package com.photogram.fake.api.modules.entities.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class SessionResponse {
    private final String token;
}
