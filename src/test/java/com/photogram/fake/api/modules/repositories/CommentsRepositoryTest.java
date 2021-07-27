package com.photogram.fake.api.modules.repositories;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.photogram.fake.api.modules.entities.domain.Comment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

class CommentsRepositoryTest {
    private RestTemplate restTemplate = mock(RestTemplate.class);
    private Gson gson = new GsonBuilder().create();

    @Test
    void get() {
        String url = "https://jsonplaceholder.typicode.com/posts/999/comments";
        String bodyResponse = "[\n" +
                "  {\n" +
                "    \"postId\": 22,\n" +
                "    \"id\": 106,\n" +
                "    \"name\": \"maiores placeat facere quam pariatur\",\n" +
                "    \"email\": \"Allen@richard.biz\",\n" +
                "    \"body\": \"dolores debitis voluptatem ab hic\\nmagnam alias qui est sunt\\net porro velit et repellendus occaecati est\\nsequi quia odio deleniti illum\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"postId\": 22,\n" +
                "    \"id\": 107,\n" +
                "    \"name\": \"in ipsam vel id impedit possimus eos voluptate\",\n" +
                "    \"email\": \"Nicholaus@mikayla.ca\",\n" +
                "    \"body\": \"eveniet fugit qui\\nporro eaque dolores eos adipisci dolores ut\\nfugit perferendis pariatur\\nnumquam et repellat occaecati atque ipsum neque\"\n" +
                "  }\n" +
                "]";

        when(restTemplate.getForEntity(url, String.class))
                .thenReturn(new ResponseEntity(bodyResponse, HttpStatus.OK));

        CommentsRepository commentsRepository = new CommentsRepository(restTemplate, gson);
        List<Comment> comments = commentsRepository.get(999);
        assertEquals(2, comments.size());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("{\"postId\":999,\"id\":0,\"name\":\"peter\",\"email\":\"peter@email.com\",\"body\":\"some comment\"}", headers);
        when(restTemplate.postForObject("https://jsonplaceholder.typicode.com/comments", entity, String.class))
                .thenReturn("{\n" +
                        "  \"postId\": 1,\n" +
                        "  \"id\": 501,\n" +
                        "  \"name\": \"peter\",\n" +
                        "  \"email\": \"peter@email.com\",\n" +
                        "  \"body\": \"some comment\"\n" +
                        "}");

        CommentsRepository commentsRepository = new CommentsRepository(restTemplate, gson);
        Comment comment = commentsRepository.create(999);

        assertEquals(501, comment.getId());
    }

    @Test
    void update() {
        Comment comment = Comment.builder()
                .name("name")
                .id(123L)
                .build();

        CommentsRepository commentsRepository = new CommentsRepository(restTemplate, gson);
        Comment response = commentsRepository.update(comment);

        assertEquals(comment, response);
    }

    @Test
    void delete() {

        CommentsRepository commentsRepository = new CommentsRepository(restTemplate, gson);
        commentsRepository.delete(999);
    }
}