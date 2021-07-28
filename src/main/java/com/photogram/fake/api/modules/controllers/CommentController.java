package com.photogram.fake.api.modules.controllers;

import com.google.gson.Gson;
import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.entities.responses.UpdateCommentResponse;
import com.photogram.fake.api.modules.exceptions.ApplicationException;
import com.photogram.fake.api.modules.services.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/photogram/api/v1")
@AllArgsConstructor
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private Gson gson;

    /*
    Delete a comment

     * @return empty json when it was deleted
     * @throws ApplicationException a exception
     */
    @DeleteMapping(value = "/comment/{commentId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteComment(
            @PathVariable("commentId")
                    long commentId,
            @RequestHeader("session-token")
                    String token) throws ApplicationException {
        log.info("Delete a comment. commentId: " + commentId);

        Comment comment = Comment.builder()
                .id(commentId)
                .build();

        commentService.delete(CommentService.Model.builder()
                .comment(comment)
                .token(token)
                .build());

        return ResponseEntity.ok("{}");
    }

    /*
    Update a comment

     * @return the updated comment
     * @throws ApplicationException a exception
     */
    @PutMapping(value = "/comment/{commentId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateComment(
            @PathVariable("commentId")
                    long commentId,
            @RequestBody
                    Comment comment,
            @RequestHeader("session-token")
                    String token) throws ApplicationException {
        log.info("Update a comment. commentId: " + commentId);
        log.info("Body comment: " + comment.toString());
        log.info("session-token: " + token);

        Comment requestComment = Comment.builder()
                .body(comment.getBody())
                .email(comment.getEmail())
                .name(comment.getName())
                .postId(comment.getPostId())
                .id(commentId)
                .token(token)
                .build();

        UpdateCommentResponse response = UpdateCommentResponse.builder()
                .updated(commentService.update(CommentService.Model.builder()
                        .comment(requestComment)
                        .token(token)
                        .build()))
                .build();

        return ResponseEntity.ok(gson.toJson(response));
    }
}
