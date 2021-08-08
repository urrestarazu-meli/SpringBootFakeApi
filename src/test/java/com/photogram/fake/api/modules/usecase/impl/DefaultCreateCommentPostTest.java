package com.photogram.fake.api.modules.usecase.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.repositories.CommentsRepository;
import com.photogram.fake.api.modules.repositories.SessionRepository;
import com.photogram.fake.api.modules.usecase.CreateCommentPost;
import org.junit.jupiter.api.Test;

class DefaultCreateCommentPostTest {
    private CommentsRepository commentsRepository = mock(CommentsRepository.class);
    private SessionRepository sessionRepository = mock(SessionRepository.class);

    @Test
    void create() {
        long postId = 999L;
        String newComment = "Hello world!";

        Comment comment = Comment.builder()
                .id(1)
                .postId(postId)
                .body(newComment)
                .build();

        User user = User.builder()
                .name("Peter")
                .email("peter@mail.com")
                .build();

        when(commentsRepository.create(postId, newComment, user))
                .thenReturn(comment);

        when(sessionRepository.getUser(anyString()))
                .thenReturn(user);

        DefaultCreateCommentPost createCommentPost = new DefaultCreateCommentPost(commentsRepository, sessionRepository);
        Comment result = createCommentPost.create(CreateCommentPost.Model.builder()
                .postId(postId)
                .token("token")
                .newComment(newComment)
                .build());

        assertNotNull(result);
        assertEquals(postId, result.getPostId());
    }
}