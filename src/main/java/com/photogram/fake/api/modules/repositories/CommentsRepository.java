package com.photogram.fake.api.modules.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.photogram.fake.api.modules.entities.ErrorCode;
import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.entities.domain.Post;
import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.exceptions.ApplicationException;
import com.photogram.fake.api.modules.stereotypes.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentsRepository {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    public List<Comment> get(long postId) {
        try {
            String url = String.format("https://jsonplaceholder.typicode.com/posts/%d/comments", postId);

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            Type commentListType = new TypeToken<ArrayList<Comment>>() {
            }.getType();
            return gson.fromJson(response.getBody(), commentListType);
        } catch (Exception exc) {
            throw new ApplicationException(ErrorCode.NO_SOLUTION_FOUND, "couldn't get comments", null);
        }
    }

    public Comment create(long postId) {
        try {
            String url = "https://jsonplaceholder.typicode.com/comments";

            //TODO : sacar de la sesion
            String requestJson = gson.toJson(Comment.builder()
                    .postId(postId)
                    .name("peter")
                    .email("peter@email.com")
                    .body("some comment")
                    .build());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
            String response = restTemplate.postForObject(url, entity, String.class);

            return gson.fromJson(response, Comment.class);
        } catch (Exception exc) {
            throw new ApplicationException(ErrorCode.NO_SOLUTION_FOUND, "couldn't create a comment", null);
        }
    }

    public Comment update(Comment comment) {
        try {
            String url = String.format("https://jsonplaceholder.typicode.com/comments/%d", comment.getId());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Comment> body = new HttpEntity<>(comment, headers);

            restTemplate.exchange(url, HttpMethod.PUT, body, Void.class);

            return comment;
        } catch (Exception exc) {
            throw new ApplicationException(ErrorCode.NO_SOLUTION_FOUND, "couldn't update the comment", null);
        }
    }

    public void delete(long commentId) {
        try {
            String url = String.format("https://jsonplaceholder.typicode.com/comments/%d", commentId);

            restTemplate.delete(url);
        } catch (Exception exc) {
            throw new ApplicationException(ErrorCode.NO_SOLUTION_FOUND, "couldn't update the comment", null);
        }
    }
}
