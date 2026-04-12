package com.example.jounaling_app.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.jounaling_app.entity.UserEntity;
import com.example.jounaling_app.repository.UserRepo;

@Component
public class UserService {
  
  @Autowired
  private UserRepo userRepo;

  private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public UserEntity saveUser(UserEntity user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(Arrays.asList("USER"));
    return userRepo.save(user);
  }

  public List<UserEntity> getAllUsers() {
    return userRepo.findAll();
  }

  public UserEntity findByUsername(String username) {
    return userRepo.findByUsername(username);
  }
}
