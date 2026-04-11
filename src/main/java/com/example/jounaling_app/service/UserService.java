package com.example.jounaling_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jounaling_app.entity.UserEntity;
import com.example.jounaling_app.repository.UserRepo;

@Component
public class UserService {
  
  @Autowired
  private UserRepo userRepo;

  public UserEntity saveUser(UserEntity user) {
    return userRepo.save(user);
  }

  public List<UserEntity> getAllUsers() {
    return userRepo.findAll();
  }

  public UserEntity findByUsername(String username) {
    return userRepo.findByUsername(username);
  }
}
