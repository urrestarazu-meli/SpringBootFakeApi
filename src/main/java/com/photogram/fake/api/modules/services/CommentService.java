package com.photogram.fake.api.modules.services;

import com.photogram.fake.api.modules.entities.domain.Comment;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    /*
    Create a comment
     *
     * @param model a Comment Service model
     * @return
     */
    Comment add(Model model);

    /*
    Gets post's comments
     *
     * @param model a Comment Service model
     * @return
     */
    List<Comment> get(Model model);

    /*
    Modify a comment

     * @param model  a Comment Service model
     * @return
     */
    Comment update(Model model);

    /*
    Delete a comment

     *
     * @param model  a Comment Service model
     */
    void delete(Model model);

    /*
     Comment Service model
     */
    @Builder
    @Getter
    class Model {
        private final long postId;
        private final Comment comment;
        private final String token;
        private final String newComment;
    }
}
