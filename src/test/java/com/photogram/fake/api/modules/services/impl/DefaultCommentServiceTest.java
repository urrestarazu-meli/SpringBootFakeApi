package com.photogram.fake.api.modules.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.repositories.CommentsRepository;
import com.photogram.fake.api.modules.usecase.CreateCommentPost;
import com.photogram.fake.api.modules.usecase.DeleteComment;
import com.photogram.fake.api.modules.usecase.GetCommentsPost;
import com.photogram.fake.api.modules.usecase.ModifyComment;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class DefaultCommentServiceTest {
    private CommentsRepository commentsRepository = mock(CommentsRepository.class);
    private CreateCommentPost createCommentPost = mock(CreateCommentPost.class);
    private GetCommentsPost getCommentsPost = mock(GetCommentsPost.class);
    private ModifyComment modifyComment = mock(ModifyComment.class);
    private DeleteComment deleteComment = mock(DeleteComment.class);

    @Test
    void add() {
        Long postId = 999L;
        Comment comment = Comment.builder()
                .postId(postId)
                .build();

        when(createCommentPost.create(any()))
                .thenReturn(comment);

        DefaultCommentService commentService = new DefaultCommentService(commentsRepository,
                createCommentPost,
                getCommentsPost,
                modifyComment,
                deleteComment);

        Comment result = commentService.add(postId);

        assertEquals(comment, result);
    }

    @Test
    void get() {
        Long postId = 999L;
        Comment comment = Comment.builder()
                .id(1L)
                .postId(postId)
                .build();
        when(getCommentsPost.get(any()))
                .thenReturn(Collections.singletonList(comment));

        DefaultCommentService commentService = new DefaultCommentService(commentsRepository,
                createCommentPost,
                getCommentsPost,
                modifyComment,
                deleteComment);

        List<Comment> result = commentService.get(postId);
        assertEquals(Collections.singletonList(comment), result);
    }

    @Test
    void update() {
        Long postId = 999L;
        Comment comment = Comment.builder()
                .id(1L)
                .postId(postId)
                .build();

        when(modifyComment.update(any()))
                .thenReturn(comment);

        DefaultCommentService commentService = new DefaultCommentService(commentsRepository,
                createCommentPost,
                getCommentsPost,
                modifyComment,
                deleteComment);

        Comment result = commentService.update(comment);

        assertEquals(comment, result);
    }

    @Test
    void delete() {
        Long commentId = 999L;

        DefaultCommentService commentService = new DefaultCommentService(commentsRepository,
                createCommentPost,
                getCommentsPost,
                modifyComment,
                deleteComment);

        commentService.delete(commentId);
    }
}