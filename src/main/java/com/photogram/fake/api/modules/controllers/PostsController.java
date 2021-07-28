package com.photogram.fake.api.modules.controllers;

import com.google.gson.Gson;
import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.entities.responses.CreateCommentResponse;
import com.photogram.fake.api.modules.entities.responses.GetCommentsResponse;
import com.photogram.fake.api.modules.exceptions.ApplicationException;
import com.photogram.fake.api.modules.services.CommentService;
import com.photogram.fake.api.modules.services.FanService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/photogram/api/v1")
public class PostsController {
    @Autowired
    private FanService fanService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private Gson gson;

    /*
    comment on a posts

     * @param postId a post id
     * @return id of the created post
     * @throws ApplicationException a Application Exception
     */
    @PostMapping(value = "/post/{postId}/comment",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPostsComment(
            @PathVariable("postId")
                    long postId,
            @RequestBody
                    String comment,
            @RequestHeader("session-token")
                    String token) throws ApplicationException {
        log.info("Create a comment for the post: " + postId);

        CreateCommentResponse response = CreateCommentResponse.builder()
                .commentId(commentService.add(CommentService.Model.builder()
                        .postId(postId)
                        .newComment(comment)
                        .token(token)
                        .build()).getId())
                .build();

        return ResponseEntity.ok(gson.toJson(response));
    }

    /*
    List all comments of a post

     * @param postId a post id
     * @return list of comments
     * @throws ApplicationException a Application Exception
     */
    @GetMapping(value = "/post/{postId}/comments",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPostsComment(
            @PathVariable("postId")
                    long postId,
            @RequestHeader("session-token")
                    String token) throws ApplicationException {
        log.info("Getting comments from the post: " + postId);

        GetCommentsResponse response = GetCommentsResponse.builder()
                .comments(commentService.get(CommentService.Model.builder()
                        .postId(postId)
                        .token(token)
                        .build()))
                .build();

        return ResponseEntity.ok(gson.toJson(response));
    }
}
