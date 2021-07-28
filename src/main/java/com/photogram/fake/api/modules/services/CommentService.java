package com.photogram.fake.api.modules.services;

import com.photogram.fake.api.modules.entities.domain.Comment;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    /**
     *
     * @param model
     * @return
     */
    Comment add(Model model);

    /**
     *
     * @param model
     * @return
     */
    List<Comment> get(Model model);

    /**
     *
     * @param model
     * @return
     */
    Comment update(Model model);

    /**
     *
     * @param model
     */
    void delete(Model model);

    @Builder
    @Getter
    class Model {
        private final long postId;
        private final Comment comment;
        private final String token;
    }
}
