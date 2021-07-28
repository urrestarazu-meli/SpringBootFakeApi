package com.photogram.fake.api.modules.services.impl;

import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.exceptions.BusinessException;
import com.photogram.fake.api.modules.exceptions.SessionException;
import com.photogram.fake.api.modules.repositories.CommentsRepository;
import com.photogram.fake.api.modules.services.CommentService;
import com.photogram.fake.api.modules.usecase.CreateCommentPost;
import com.photogram.fake.api.modules.usecase.DeleteComment;
import com.photogram.fake.api.modules.usecase.GetCommentsPost;
import com.photogram.fake.api.modules.usecase.ModifyComment;
import com.photogram.fake.api.modules.usecase.ValidateSession;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultCommentService implements CommentService {
    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    CreateCommentPost createCommentPost;

    @Autowired
    GetCommentsPost getCommentsPost;

    @Autowired
    ModifyComment modifyComment;

    @Autowired
    DeleteComment deleteComment;

    @Autowired
    ValidateSession validateSession;

    @Override
    public Comment add(Model model) {

        validateSession(model);

        return createCommentPost.create(CreateCommentPost.Model.builder()
                .postId(model.getPostId())
                .build());
    }

    @Override
    public List<Comment> get(Model model) {
        validateSession(model);

        return getCommentsPost.get(GetCommentsPost.Model.builder()
                .postId(model.getPostId())
                .build());
    }

    @Override
    public Comment update(Model model) {
        validateSession(model);

        return modifyComment.update(ModifyComment.Model.builder()
                .comment(model.getComment())
                .build());
    }

    @Override
    public void delete(Model model) {
        validateSession(model);

        deleteComment.delete(DeleteComment.Model.builder()
                .commentId(model.getComment().getId())
                .build());
    }

    /**
     * @param model
     */
    private void validateSession(Model model) {
        validateSession.validate(ValidateSession.Model.builder()
                .sessionToken(model.getToken())
                .build());
    }
}
