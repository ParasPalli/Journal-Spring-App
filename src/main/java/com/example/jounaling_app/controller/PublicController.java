package com.example.jounaling_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jounaling_app.entity.UserEntity;
import com.example.jounaling_app.service.UserService;
import com.example.jounaling_app.utils.JWTUtils;

@RestController
@RequestMapping("/public")
public class PublicController {

  @Autowired
  private UserService userService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JWTUtils jwtUtils;

  @PostMapping("/create-user")
  public ResponseEntity<?> createUser(@RequestBody UserEntity entity) {
    try {
      return ResponseEntity.ok(userService.saveUser(entity));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("/login-user")
  public ResponseEntity<?> loginUser(@RequestBody UserEntity user) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        user.getUsername(), user.getPassword()
      ));

      //
      UserEntity authenticatedUser = userService.findByUsername(user.getUsername());
      return ResponseEntity.ok(jwtUtils.generateToken(authenticatedUser.getUsername()));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
