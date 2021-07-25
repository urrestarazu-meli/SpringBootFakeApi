package com.photogram.fake.api.modules.entities.responses;

import com.photogram.fake.api.modules.entities.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Builder
@AllArgsConstructor
public class GetCommentsResponse {
    List<Comment> comments;
}
