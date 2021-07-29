package com.photogram.fake.api.modules.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.services.FanService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class FanControllerTest {
    private FanService fanService = mock(FanService.class);
    private Gson gson = new GsonBuilder().create();

    @Test
    void getFans() {
        when(fanService.get(any()))
                .thenReturn(Collections.singletonList(User.builder()
                        .id(999L)
                        .build()));

        FanController fanController = new FanController(fanService, gson);

        ResponseEntity<String> response = fanController.getFans("token");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"following\":[{\"id\":999}]}", response.getBody());
    }

    @Test
    void addFan() {
        FanController fanController = new FanController(fanService, gson);
        ResponseEntity<User> response = fanController.addFan(999,"token");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void generateReport(){
        FanController fanController = new FanController(fanService, gson);
        ResponseEntity<String> response =fanController.generateReport("format","token");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}