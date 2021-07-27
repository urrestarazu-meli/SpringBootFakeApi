package com.photogram.fake.api.modules.entities.responses;

import com.photogram.fake.api.modules.entities.domain.Comment;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class GetCommentsResponse {
    List<Comment> comments;
}
