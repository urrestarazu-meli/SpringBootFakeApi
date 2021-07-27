package com.photogram.fake.api.modules.entities.responses;

import com.photogram.fake.api.modules.entities.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class UpdateCommentResponse {
    Comment updated;
}
