package com.cjrp.base_001_app_todoist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// Role. 5 creacion de admin controller

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public ResponseEntity<String> adminwelcome() {
        return ResponseEntity.ok("¡Bienvenido, Administrador Christian!");
    }
}
