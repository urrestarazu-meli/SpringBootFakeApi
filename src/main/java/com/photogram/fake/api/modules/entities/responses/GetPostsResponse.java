package com.photogram.fake.api.modules.entities.responses;

import com.photogram.fake.api.modules.entities.domain.Post;
import java.util.List;
import lombok.Builder;

@Builder
public class GetPostsResponse {
    List<Post> posts;
}
