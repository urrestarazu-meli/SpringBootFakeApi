package com.photogram.fake.api.modules.controllers;

import com.google.gson.Gson;
import com.photogram.fake.api.modules.entities.responses.SessionResponse;
import com.photogram.fake.api.modules.exceptions.ApplicationException;
import com.photogram.fake.api.modules.services.SessionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/photogram/api/v1")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private Gson gson;

    /*
    Gets a token session

     *
     * @param userId a user id
     * @return a token session
     * @throws ApplicationException a Application Exception
     */
    @GetMapping(value = "/user/{userId}/session",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPostsComment(
            @PathVariable("userId")
                    long userId) throws ApplicationException {
        log.info("Getting a session for user: " + userId);

        SessionResponse response = SessionResponse.builder()
                .token(sessionService.generate(SessionService.Model.builder()
                        .userId(userId)
                        .build()))
                .build();

        return ResponseEntity.ok(gson.toJson(response));
    }
}
