package com.photogram.fake.api.modules.usecase;

import com.photogram.fake.api.modules.stereotypes.Usecase;
import lombok.Builder;
import lombok.Getter;

@Usecase
public interface ValidateSession {
    void validate(Model model);

    @Builder
    @Getter
    class Model {
        private final String sessionToken;
    }
}
