package com.photogram.fake.api.modules.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.services.CommentService;
import com.photogram.fake.api.modules.services.FanService;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class PostsControllerTest {
    private FanService fanService = mock(FanService.class);
    private CommentService commentService = mock(CommentService.class);
    private Gson gson = new GsonBuilder().create();

    @Test
    void createPostsComment() {
        long postId = 999L;
        when(commentService.add(any()))
                .thenReturn(Comment.builder()
                        .body("body")
                        .email("email")
                        .id(1L)
                        .postId(2L)
                        .name("name")
                        .build());

        PostsController postsController = new PostsController(fanService, commentService, gson);
        ResponseEntity<String> response = postsController.createPostsComment(postId, "hello!", "token");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"commentId\":1}", response.getBody());
    }

    @Test
    void getPostsComment() {
        int postId = 999;
        Comment comment = Comment.builder()
                .body("body")
                .email("email")
                .id(1L)
                .postId(2L)
                .name("name")
                .build();

        when(commentService.get(any()))
                .thenReturn(Collections.singletonList(comment));

        PostsController postsController = new PostsController(fanService, commentService, gson);
        ResponseEntity<String> response = postsController.getPostsComment(postId, "token");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"comments\":[{\"postId\":2,\"id\":1,\"name\":\"name\",\"email\":\"email\",\"body\":\"body\"}]}",
                response.getBody());
    }
}