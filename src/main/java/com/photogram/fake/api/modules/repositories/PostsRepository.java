package com.photogram.fake.api.modules.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.exceptions.RepositoryException;
import com.photogram.fake.api.modules.stereotypes.Repository;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/*
 Post's Repository
 */
@Repository
public class PostsRepository {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    /*
    Gets comments of a publication

     * @param postId a post id
     * @return the comments
     * @throws RepositoryException a Repository Exception
     */
    public List<Comment> getComments(long postId) throws RepositoryException {
        try {
            String url = String.format("https://jsonplaceholder.typicode.com/posts/%d/comments", postId);

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            Type commentListType = new TypeToken<ArrayList<Comment>>() {
            }.getType();
            return gson.fromJson(response.getBody(), commentListType);
        } catch (Exception exc) {
            throw new RepositoryException("couldn't get post's comments", null);
        }
    }
}
