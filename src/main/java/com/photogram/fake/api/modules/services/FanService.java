package com.photogram.fake.api.modules.services;

import com.photogram.fake.api.modules.entities.domain.Post;
import com.photogram.fake.api.modules.entities.domain.User;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public interface FanService {
    /*
     Add a fan

     * @param userId user id to add
     * @return the user added
     */
    User add(Model model);

    /*
     Get my fans

      * @return
      */
    List<User> get(Model model);

    /*
     Gets posts from a fan
     * @param userId a user id
     * @return the posts
     */
    List<Post> getPostsFan(Model model);

    @Builder
    @Getter
    class Model {
        private final long userId;
        private final String token;
    }
}
