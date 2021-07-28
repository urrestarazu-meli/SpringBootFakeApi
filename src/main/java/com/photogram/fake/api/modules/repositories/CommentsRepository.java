package com.photogram.fake.api.modules.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.exceptions.RepositoryException;
import com.photogram.fake.api.modules.stereotypes.Repository;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/*
 Comment's repository
 */
@Repository
@AllArgsConstructor
public class CommentsRepository {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    /*
     Get comment's post

     * @param postId a post id
     * @return list of comments of a posts
     * @throws RepositoryException a repository exception
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

    /*
     Creates a comment to a post

     * @param postId a post id
     * @return a new comment
     * @comment a comment
     * @throws RepositoryException a repository exception
     */
    public Comment create(long postId, String comment) throws RepositoryException {
        try {
            String url = "https://jsonplaceholder.typicode.com/comments";


            String requestJson = gson.toJson(Comment.builder()
                    .postId(postId)
                    .name("peter")
                    .email("peter@email.com")
                    .body(comment)
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

    /*
    Modify a comment

     * @param comment a modified comment
     * @return the updated comment
     * @throws RepositoryException a repository exception
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

    /*
    Deletes a comment

     * @param commentId comment id to delete
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
