package com.photogram.fake.api.modules.usecase;

import com.photogram.fake.api.modules.stereotypes.Usecase;
import lombok.Builder;
import lombok.Getter;

@Usecase
public interface DeleteComment {
    void delete(Model model);

    @Builder
    @Getter
    class Model {
        private final long commentId;
    }
}
