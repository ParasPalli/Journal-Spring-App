package com.example.jounaling_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jounaling_app.entity.UserEntity;
import com.example.jounaling_app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
  
  @Autowired
  private UserService userService;
  
  @GetMapping
  public List<UserEntity> getAllUsers() {
    return userService.getAllUsers();
  }

  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody UserEntity entity) {
    try {
      return ResponseEntity.ok(userService.saveUser(entity));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
  
  @PutMapping
  public ResponseEntity<?> updateUser(@RequestBody UserEntity user) {
    UserEntity existingUser = userService.findByUsername(user.getUsername());
    if (existingUser != null) {
      existingUser.setUsername(user.getUsername());
      existingUser.setPassword(user.getPassword());
      return ResponseEntity.ok(userService.saveUser(existingUser));
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
