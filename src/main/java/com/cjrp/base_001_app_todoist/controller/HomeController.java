package com.cjrp.base_001_app_todoist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    @GetMapping
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("¡Bienvenido a nuestra aplicación, Christian!");
    }
}