package com.example.jounaling_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jounaling_app.api_reponse.WeatherResponsePojo;
import com.example.jounaling_app.entity.UserEntity;
import com.example.jounaling_app.service.UserService;
import com.example.jounaling_app.service.WeatherService;

@RestController
@RequestMapping("/user")
public class UserController {
  
  @Autowired
  private UserService userService;

  @Autowired
  private WeatherService weatherService;
  
  @PutMapping
  public ResponseEntity<?> updateUser(@RequestBody UserEntity user) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUsername = authentication.getName();

    //
    UserEntity existingUser = userService.findByUsername(currentUsername);
    if (existingUser != null) {
      existingUser.setUsername(user.getUsername());
      existingUser.setPassword(user.getPassword());
      return ResponseEntity.ok(userService.saveUser(existingUser));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping
  public ResponseEntity<?> getWeather() {
    WeatherResponsePojo weatherResponse = weatherService.getWeather("New York");
    if (weatherResponse != null) {
      return ResponseEntity.ok(weatherResponse);
    } else {
      return ResponseEntity.status(500).body("Failed to fetch weather data");
    }
  }
}
