package com.example.jounaling_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.jounaling_app.entity.UserEntity;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity = userService.findByUsername(username);
    if (userEntity == null) throw new UsernameNotFoundException("User not found: " + username);
    
    return User.builder()
      .username(userEntity.getUsername())
      .password(userEntity.getPassword())
      .roles(userEntity.getRoles().toArray(new String[0]))
      .build();
  }

}
