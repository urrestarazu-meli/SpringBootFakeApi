package com.photogram.fake.api.modules.usecase.impl;

import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.repositories.CommentsRepository;
import com.photogram.fake.api.modules.usecase.GetCommentsPost;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultGetCommentsPostTest {
    private CommentsRepository commentsRepository = mock(CommentsRepository.class);

    @Test
    void get() {
        long postId = 999L;
        Comment comment = Comment.builder()
                .id(1L)
                .postId(postId)
                .build();

        when(commentsRepository.get(postId))
                .thenReturn(Collections.singletonList(comment));

        DefaultGetCommentsPost getCommentsPost = new DefaultGetCommentsPost(commentsRepository);

        List<Comment> result = getCommentsPost.get(GetCommentsPost.Model.builder()
                .postId(postId)
                .build());

        assertNotNull(result);
        assertEquals(Collections.singletonList(comment), result);
    }
}
