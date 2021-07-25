package com.photogram.fake.api.modules.controllers;

import com.google.gson.Gson;
import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.entities.responses.CreateCommentResponse;
import com.photogram.fake.api.modules.entities.responses.GetCommentsResponse;
import com.photogram.fake.api.modules.entities.responses.GetFollowersResponse;
import com.photogram.fake.api.modules.entities.responses.GetPostsResponse;
import com.photogram.fake.api.modules.entities.responses.UpdateCommentResponse;
import com.photogram.fake.api.modules.services.CommentService;
import com.photogram.fake.api.modules.services.FanService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.photogram.fake.api.modules.exceptions.ApplicationException;

@Slf4j
@RestController
@RequestMapping("/photogram/api/v1")
@AllArgsConstructor
public class RouteController {
    @Autowired
    private FanService fanService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private Gson gson;

    @GetMapping("/ping")
    public ResponseEntity<String> ping() throws ApplicationException {
        log.info("Doing ping.");

        return ResponseEntity.ok("pong!");
    }

    @GetMapping(value = "/fan",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getFans() throws ApplicationException {
        log.info("Get fans");

        GetFollowersResponse response = GetFollowersResponse.builder()
                .following(fanService.get())
                .build();

        return ResponseEntity.ok(gson.toJson(response));
    }

    @PutMapping("/fan/{userId}")
    public ResponseEntity<User> addFan(@PathVariable("userId") long userId) throws ApplicationException {
        log.info("Add a fan");
        User user = fanService.add(userId);

        return ResponseEntity.ok(user);
    }

    /**
     * get posts from a fan
     *
     * @return
     * @throws ApplicationException
     */
    @GetMapping(value = "/fan/{userId}/posts",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getFanPosts(@PathVariable("userId") long userId) throws ApplicationException {
        log.info("Get posts from a fan");

        GetPostsResponse response = GetPostsResponse.builder()
                .posts(fanService.getPostsFan(userId))
                .build();

        return ResponseEntity.ok(gson.toJson(response));
    }

    /**
     * @return
     * @throws ApplicationException
     */
    @PostMapping(value = "/posts/{postId}/comment",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createComment(@PathVariable("postId") long postId) throws ApplicationException {
        log.info("Get posts from a fan");

        CreateCommentResponse response = CreateCommentResponse.builder()
                .commentId(commentService.add(postId).getId())
                .build();

        return ResponseEntity.ok(gson.toJson(response));
    }

    /**
     * @return
     * @throws ApplicationException
     */
    @DeleteMapping(value = "/comment/{commentId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteComment(@PathVariable("commentId") long commentId) throws ApplicationException {
        log.info("Delete a comment");

        commentService.delete(commentId);

        return ResponseEntity.ok("{}");
    }

    /**
     * @return
     * @throws ApplicationException
     */
    @PutMapping(value = "/comment/{commentId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateComment(@PathVariable("commentId") long commentId, @RequestBody Comment comment) throws ApplicationException {
        log.info("Get posts from a fan");


        UpdateCommentResponse response = UpdateCommentResponse.builder()
                .updated(commentService.update(Comment.builder()
                        .body(comment.getBody())
                        .email(comment.getEmail())
                        .name(comment.getName())
                        .postId(comment.getPostId())
                        .id(commentId)
                        .build()))
                .build();

        return ResponseEntity.ok(gson.toJson(response));
    }

    /**
     * @return
     * @throws ApplicationException
     */
    @GetMapping(value = "/post/{postId}/comments",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPostsComment(@PathVariable("postId") long postId) throws ApplicationException {
        log.info("Get comments from a post");

        GetCommentsResponse response = GetCommentsResponse.builder()
                .comments(commentService.get(postId))
                .build();

        return ResponseEntity.ok(gson.toJson(response));
    }
}
