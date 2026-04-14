package com.example.jounaling_app.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.jounaling_app.entity.UserEntity;
import com.example.jounaling_app.enums.RoleEnum;
import com.example.jounaling_app.repository.UserRepo;

@Component
public class UserService {
  
  @Autowired
  private UserRepo userRepo;

  @Autowired
  private PasswordEncoder encoder;

  public UserEntity getCurrentUser() {
    return userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
  }

  public UserEntity saveUser(UserEntity user) {
    user.setPassword(encoder.encode(user.getPassword()));
    user.setRoles(Arrays.asList(RoleEnum.USER.name()));
    return userRepo.save(user);
  }

  public UserEntity justSaveUser(UserEntity user) {
    return userRepo.save(user);
  }

  public UserEntity saveAdminUser(UserEntity user) {
    user.setPassword(encoder.encode(user.getPassword()));
    user.setRoles(Arrays.asList(RoleEnum.ADMIN.name(), RoleEnum.USER.name()));
    return userRepo.save(user);
  }

  public List<UserEntity> getAllUsers() {
    return userRepo.findAll();
  }

  public UserEntity findByUsername(String username) {
    return userRepo.findByUsername(username);
  }
}
