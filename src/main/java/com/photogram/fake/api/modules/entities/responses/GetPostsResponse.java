package com.photogram.fake.api.modules.entities.responses;

import com.photogram.fake.api.modules.entities.domain.Post;
import lombok.Builder;

import java.util.List;

@Builder
public class GetPostsResponse {
    List<Post> posts;
}
