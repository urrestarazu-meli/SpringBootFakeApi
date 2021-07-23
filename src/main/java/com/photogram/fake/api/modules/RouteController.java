package com.photogram.fake.api.modules;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/photogram/api/v1")
@AllArgsConstructor
public class RouteController {
    @GetMapping("/ping")
    public ResponseEntity<String> getTopItems() throws ApplicationException {

        log.info("Doing ping.");

        return ResponseEntity.ok("pong!");
    }
}
