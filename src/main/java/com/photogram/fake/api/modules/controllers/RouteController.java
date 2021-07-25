package com.photogram.fake.api.modules.controllers;

import com.google.gson.Gson;
import com.photogram.fake.api.modules.entities.ErrorCode;
import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.entities.responses.GetFollowersResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.photogram.fake.api.modules.exceptions.ApplicationException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/photogram/api/v1")
@AllArgsConstructor
public class RouteController {
    static List<User> fans = new ArrayList<User>();

    @GetMapping("/ping")
    public ResponseEntity<String> ping() throws ApplicationException {
        log.info("Doing ping.");

        return ResponseEntity.ok("pong!");
    }

    @RequestMapping(value = "/fan",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getFollowers() throws ApplicationException {
        log.info("Get fans");

        Gson gson = new Gson();

        GetFollowersResponse response = GetFollowersResponse.builder()
                .following(fans)
                .build();
        return ResponseEntity.ok(gson.toJson(response));
    }

    @PutMapping("/fan/{userId}")
    public ResponseEntity<User> addFollower(@PathVariable("userId") long userId) throws ApplicationException {
        try {
            //repository
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://jsonplaceholder.typicode.com/users/" + Long.toString(userId);

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            //serializer
            User user;

            Gson gson = new Gson();
            user = gson.fromJson(response.getBody(), User.class);
            fans.add(user);

            return ResponseEntity.ok(user);
        } catch (Exception exc) {
            throw new ApplicationException(ErrorCode.NO_SOLUTION_FOUND, "can't add fan", null);
        }
    }
}
