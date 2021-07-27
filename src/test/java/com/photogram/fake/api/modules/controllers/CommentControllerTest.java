package com.photogram.fake.api.modules.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.services.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class CommentControllerTest {
    private CommentService commentService = mock(CommentService.class);
    private Gson gson = new GsonBuilder().create();

    @Test
    void deleteComment() {
        CommentController commentController = new CommentController(commentService, gson);

        ResponseEntity<String> response = commentController.deleteComment(999);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{}", response.getBody());
    }

    @Test
    void updateComment() {
        Comment comment = Comment.builder().build();
        when(commentService.update(any()))
                .thenReturn(comment);

        CommentController commentController = new CommentController(commentService, gson);

        ResponseEntity<String> response = commentController.updateComment(999, comment);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"updated\":{\"postId\":0,\"id\":0}}", response.getBody());
    }
}