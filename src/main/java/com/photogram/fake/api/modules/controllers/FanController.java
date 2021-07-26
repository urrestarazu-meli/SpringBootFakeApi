package com.photogram.fake.api.modules.controllers;

import com.google.gson.Gson;
import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.entities.responses.GetFollowersResponse;
import com.photogram.fake.api.modules.entities.responses.GetPostsResponse;
import com.photogram.fake.api.modules.exceptions.ApplicationException;
import com.photogram.fake.api.modules.services.FanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/photogram/api/v1")
public class FanController {
    @Autowired
    private FanService fanService;

    @Autowired
    private Gson gson;

    /**
     * @return
     * @throws ApplicationException
     */
    @GetMapping(value = "/fan",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getFans() throws ApplicationException {
        log.info("Get all fans");

        GetFollowersResponse response = GetFollowersResponse.builder()
                .following(fanService.get())
                .build();

        return ResponseEntity.ok(gson.toJson(response));
    }

    /**
     * @param userId
     * @return
     * @throws ApplicationException
     */
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
}
