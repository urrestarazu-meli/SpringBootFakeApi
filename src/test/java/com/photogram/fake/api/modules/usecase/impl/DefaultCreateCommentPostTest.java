package com.photogram.fake.api.modules.usecase.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.repositories.CommentsRepository;
import com.photogram.fake.api.modules.usecase.CreateCommentPost;
import org.junit.jupiter.api.Test;

class DefaultCreateCommentPostTest {
    private CommentsRepository commentsRepository = mock(CommentsRepository.class);

    @Test
    void create() {
        long postId = 999L;
        Comment comment = Comment.builder()
                .id(1)
                .postId(postId)
                .build();

        when(commentsRepository.create(postId))
                .thenReturn(comment);
        DefaultCreateCommentPost createCommentPost = new DefaultCreateCommentPost(commentsRepository);
        Comment result = createCommentPost.create(CreateCommentPost.Model.builder()
                .postId(postId)
                .build());

        assertNotNull(result);
        assertEquals(postId, result.getPostId());
    }
}