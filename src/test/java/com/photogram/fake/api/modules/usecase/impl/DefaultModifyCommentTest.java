package com.photogram.fake.api.modules.usecase.impl;

import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.repositories.CommentsRepository;
import com.photogram.fake.api.modules.usecase.ModifyComment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultModifyCommentTest {
    private CommentsRepository commentsRepository = mock(CommentsRepository.class);

    @Test
    void update() {
        long postId = 999L;
        Comment comment = Comment.builder()
                .id(1)
                .postId(postId)
                .body("body comment")
                .build();

        when(commentsRepository.update(comment))
                .thenReturn(comment);

        DefaultModifyComment modifyComment = new DefaultModifyComment(commentsRepository);
        Comment result = modifyComment.update(ModifyComment.Model.builder()
                .comment(comment)
                .build());
        assertNotNull(result);
        assertEquals(comment, result);
    }
}