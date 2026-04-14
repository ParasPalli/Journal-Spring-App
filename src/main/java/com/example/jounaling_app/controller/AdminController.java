package com.example.jounaling_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jounaling_app.entity.UserEntity;
import com.example.jounaling_app.service.UserService;



@RestController
@RequestMapping("/admin")
public class AdminController {
  
  @Autowired
  private UserService userService;

  @GetMapping("/get-all-users")
  public ResponseEntity<?> fetchAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }
  
  @PostMapping("/create-admin")
  public ResponseEntity<?> postMethodName(@RequestBody UserEntity entity) {
    try {
      return ResponseEntity.ok(userService.saveAdminUser(entity));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
  
}
