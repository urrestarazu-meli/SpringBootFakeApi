package com.photogram.fake.api.modules.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.exceptions.RepositoryException;
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

/**
 *
 */
@Repository
public class CommentsRepository {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    /**
     *
     * @param postId
     * @return
     * @throws RepositoryException
     */
    public List<Comment> get(long postId) throws RepositoryException {
        try {
            String url = String.format("https://jsonplaceholder.typicode.com/posts/%d/comments", postId);

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            Type commentListType = new TypeToken<ArrayList<Comment>>() {
            }.getType();
            return gson.fromJson(response.getBody(), commentListType);
        } catch (Exception exc) {
            throw new RepositoryException("couldn't get comments", exc);
        }
    }

    /**
     *
     * @param postId
     * @return
     * @throws RepositoryException
     */
    public Comment create(long postId) throws RepositoryException {
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
            throw new RepositoryException("couldn't create a comment", exc);
        }
    }

    /**
     *
     * @param comment
     * @return
     * @throws RepositoryException
     */
    public Comment update(Comment comment) throws RepositoryException {
        try {
            String url = String.format("https://jsonplaceholder.typicode.com/comments/%d", comment.getId());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Comment> body = new HttpEntity<>(comment, headers);

            restTemplate.exchange(url, HttpMethod.PUT, body, Void.class);

            return comment;
        } catch (Exception exc) {
            throw new RepositoryException("couldn't update the comment", exc);
        }
    }

    /**
     *
     * @param commentId
     * @throws RepositoryException
     */
    public void delete(long commentId) throws RepositoryException {
        try {
            String url = String.format("https://jsonplaceholder.typicode.com/comments/%d", commentId);

            restTemplate.delete(url);
        } catch (Exception exc) {
            throw new RepositoryException("couldn't delete the comment", exc);
        }
    }
}
