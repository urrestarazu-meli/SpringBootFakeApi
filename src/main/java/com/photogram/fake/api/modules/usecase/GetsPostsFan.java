package com.photogram.fake.api.modules.usecase;

import com.photogram.fake.api.modules.entities.domain.Post;
import com.photogram.fake.api.modules.stereotypes.Usecase;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Usecase
public interface GetsPostsFan {
    List<Post> get(Model model);

    @Builder
    @Getter
    class Model {
        private final long userId;
    }
}
