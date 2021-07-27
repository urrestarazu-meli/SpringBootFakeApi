package com.photogram.fake.api.modules.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


class PingControllerTest {

    @Test
    void ping() {
        PingController pingController = new PingController();
        ResponseEntity<String> response =  pingController.ping();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("pong!", response.getBody());
    }
}