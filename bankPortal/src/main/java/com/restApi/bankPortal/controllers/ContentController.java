package com.restApi.bankPortal.controllers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class ContentController {
    @GetMapping
    public ResponseEntity<String> testAuth(){
        return ResponseEntity.ok("hello, jwt auth is working!");
    }
}