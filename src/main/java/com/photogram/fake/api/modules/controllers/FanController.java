package com.photogram.fake.api.modules.controllers;

import com.google.gson.Gson;
import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.entities.responses.GetFollowersResponse;
import com.photogram.fake.api.modules.entities.responses.GetPostsResponse;
import com.photogram.fake.api.modules.exceptions.ApplicationException;
import com.photogram.fake.api.modules.services.FanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/photogram/api/v1")
@Tag(name = "fan", description = "APIs related to fans")
public class FanController {
    @Autowired
    private FanService fanService;

    @Autowired
    private Gson gson;

    /*
    Gets my fans

     * @return my fans
     * @throws ApplicationException a application exception
     */
    @GetMapping(value = "/fan",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Gets my fans")
    public ResponseEntity<String> getFans(
            @RequestHeader("session-token")
                    String token) throws ApplicationException {
        log.info("Get all fans");

        GetFollowersResponse response = GetFollowersResponse.builder()
                .following(fanService.get(FanService.Model.builder()
                        .token(token)
                        .build()))
                .build();

        return ResponseEntity.ok(gson.toJson(response));
    }

    /*
    Adds a fanatic

     * @param userId user id to add to my fans
     * @return user the added user
     * @throws ApplicationException a application exception
     */
    @PutMapping("/fan/{userId}")
    @Operation(summary = "Adds a fanatic")
    public ResponseEntity<User> addFan(
            @PathVariable("userId")
                    long userId,
            @RequestHeader("session-token")
                    String token) throws ApplicationException {
        log.info("Add a fan");
        User user = fanService.add(FanService.Model.builder()
                .userId(userId)
                .token(token)
                .build());

        return ResponseEntity.ok(user);
    }

    /*
     Gets posts from a fan

     * @return publications of my fanatics
     * @throws ApplicationException a application exception
     */
    @GetMapping(value = "/fan/{userId}/posts",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Gets posts from a fan")
    public ResponseEntity<String> getFanPosts(
            @PathVariable("userId")
                    long userId,
            @RequestHeader("session-token")
                    String token) throws ApplicationException {
        log.info("Get posts from a fan");

        GetPostsResponse response = GetPostsResponse.builder()
                .posts(fanService.getPostsFan(FanService.Model.builder()
                        .userId(userId)
                        .token(token)
                        .build()))
                .build();

        return ResponseEntity.ok(gson.toJson(response));
    }
}
