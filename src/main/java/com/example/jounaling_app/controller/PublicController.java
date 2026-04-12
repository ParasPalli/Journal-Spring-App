package com.example.jounaling_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jounaling_app.entity.UserEntity;
import com.example.jounaling_app.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
  
  @Autowired
  private UserService userService;

  @PostMapping("/create-user")
  public ResponseEntity<?> createUser(@RequestBody UserEntity entity) {
    try {
      return ResponseEntity.ok(userService.saveUser(entity));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
