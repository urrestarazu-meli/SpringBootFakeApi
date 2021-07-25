package com.photogram.fake.api.modules.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.photogram.fake.api.modules.entities.ErrorCode;
import com.photogram.fake.api.modules.entities.domain.Post;
import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.exceptions.ApplicationException;
import com.photogram.fake.api.modules.stereotypes.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersRepository {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Gson gson;

    public User get(long userId) {
        try {
            String url = "https://jsonplaceholder.typicode.com/users/" + Long.toString(userId);

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            return gson.fromJson(response.getBody(), User.class);
        } catch (Exception exc) {
            throw new ApplicationException(ErrorCode.NO_SOLUTION_FOUND, "couldn't add fan", null);
        }
    }

    public List<Post> getPosts(long userId) {
        try {
            String url = String.format("https://jsonplaceholder.typicode.com/users/%d/posts", userId);

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            Type postListType = new TypeToken<ArrayList<Post>>() {
            }.getType();
            return gson.fromJson(response.getBody(), postListType);
        } catch (Exception exc) {
            throw new ApplicationException(ErrorCode.NO_SOLUTION_FOUND, "couldn't get fan posts", null);
        }
    }
}
