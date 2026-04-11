package com.example.jounaling_app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.jounaling_app.entity.UserEntity;

public interface UserRepo extends MongoRepository<UserEntity, ObjectId> {
  // Query Method
  UserEntity findByUsername(String username);
}
