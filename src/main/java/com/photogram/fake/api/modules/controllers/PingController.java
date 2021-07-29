package com.photogram.fake.api.modules.controllers;

import com.photogram.fake.api.modules.exceptions.ApplicationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/photogram/api/v1")
@Tag(name = "ping", description = "Ping's API")
public class PingController {
    /*
    Used to validate that the API is up

     * @return pong message
     * @throws ApplicationException a Application Exception
     */
    @GetMapping("/ping")
    @Operation(summary = "Used to validate that the API is up")
    public ResponseEntity<String> ping() throws ApplicationException {
        log.info("Doing ping.");

        return ResponseEntity.ok("pong!");
    }

}
