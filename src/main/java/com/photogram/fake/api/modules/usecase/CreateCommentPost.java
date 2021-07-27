package com.photogram.fake.api.modules.usecase;

import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.stereotypes.Usecase;
import lombok.Builder;
import lombok.Getter;

@Usecase
public interface CreateCommentPost {
    Comment create(Model model);

    @Builder
    @Getter
    class Model {
        private final long postId;
    }
}
