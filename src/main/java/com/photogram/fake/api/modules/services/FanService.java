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

     * @param fan service's model
     * @return the user added
     */
    User add(Model model);

    /*
     Get my fans

     * @param model fan service's model
      * @return my fans
      */
    List<User> get(Model model);

    /*
     Gets posts from a fan
     * @param model fan service's model
     * @return the posts
     */
    List<Post> getPostsFan(Model model);

    /*
    Generates a Fan's report

     *
     * @param model fan service's model
     */
    void report(Model model);

    @Builder
    @Getter
    class Model {
        private final long userId;
        private final String token;
        private final String format;
    }
}
