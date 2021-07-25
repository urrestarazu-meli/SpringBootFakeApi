package com.photogram.fake.api.modules.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.photogram.fake.api.modules.entities.ErrorCode;
import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.entities.domain.Post;
import com.photogram.fake.api.modules.exceptions.ApplicationException;
import com.photogram.fake.api.modules.stereotypes.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostsCommentsRepository {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    public List<Comment> getComments(long postId) {
        try {
            String url = String.format("https://jsonplaceholder.typicode.com/posts/%d/comments", postId);

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            Type commentListType = new TypeToken<ArrayList<Comment>>() {
            }.getType();
            return gson.fromJson(response.getBody(), commentListType);
        } catch (Exception exc) {
            throw new ApplicationException(ErrorCode.NO_SOLUTION_FOUND, "couldn't get post comments", null);
        }
    }
}
