package com.photogram.fake.api.modules.usecase;

import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.stereotypes.Usecase;
import lombok.Builder;
import lombok.Getter;

@Usecase
public interface AddFan {
    User add(Model model);

    @Builder
    @Getter
    class Model {
        private final long userId;
    }
}
