package com.cluster2.ajosavingscluster2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GetStartedController {
    @GetMapping("/get_started")
    public ResponseEntity<String> getStarted(){

        return ResponseEntity.ok().body("/api/user/create-user");
    }
}
