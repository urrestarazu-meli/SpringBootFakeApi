package com.photogram.fake.api.modules.controllers;

import com.photogram.fake.api.modules.exceptions.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/photogram/api/v1")
public class PingController {
    /**
     * @return
     * @throws ApplicationException
     */
    @GetMapping("/ping")
    public ResponseEntity<String> ping() throws ApplicationException {
        log.info("Doing ping.");

        return ResponseEntity.ok("pong!");
    }

}
