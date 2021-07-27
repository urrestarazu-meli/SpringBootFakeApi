package com.photogram.fake.api.modules.usecase.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.photogram.fake.api.modules.repositories.CommentsRepository;
import com.photogram.fake.api.modules.usecase.DeleteComment;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class DefaultDeleteCommentTest {
    private RestTemplate restTemplate = mock(RestTemplate.class);
    private Gson gson = new GsonBuilder().create();

    CommentsRepository commentsRepository = spy(new CommentsRepository(restTemplate, gson));//mock(CommentsRepository.class);

    @Test
    void delete() {
        long commentId = 999L;

        DefaultDeleteComment defaultDeleteComment = new DefaultDeleteComment(commentsRepository);
        defaultDeleteComment.delete(DeleteComment.Model.builder()
                .commentId(commentId)
                .build());

        Mockito.verify(commentsRepository, atLeastOnce())
                .delete(commentId);
    }
}