package com.photogram.fake.api.modules.services.impl;

import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.repositories.CommentsRepository;
import com.photogram.fake.api.modules.services.CommentService;
import com.photogram.fake.api.modules.usecase.CreateCommentPost;
import com.photogram.fake.api.modules.usecase.DeleteComment;
import com.photogram.fake.api.modules.usecase.GetCommentsPost;
import com.photogram.fake.api.modules.usecase.ModifyComment;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
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

    @Override
    public Comment add(long postId) {
        return createCommentPost.create(CreateCommentPost.Model.builder()
                .postId(postId)
                .build());
    }

    @Override
    public List<Comment> get(long postId) {
        return getCommentsPost.get(GetCommentsPost.Model.builder()
                .postId(postId)
                .build());
    }

    @Override
    public Comment update(Comment comment) {
        return modifyComment.update(ModifyComment.Model.builder()
                .comment(comment)
                .build());
    }

    @Override
    public void delete(long commentId) {
        deleteComment.delete(DeleteComment.Model.builder()
                .commentId(commentId)
                .build());
    }
}
