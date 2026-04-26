package com.example.jounaling_app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.jounaling_app.entity.UserEntity;

public interface UserRepo extends MongoRepository<UserEntity, ObjectId> {
  // Query Method
  UserEntity findByUsername(String username);
}


// class UserRepoCriteria {
  
//   @Autowired
//   private MongoTemplate mongoTemplate;
  
// public List<UserEntity> getUsersByName {
//   Query query = new Query();

//   query.addCriteria(Criteria.where("name").is("paras"));
//   query.addCriteria(Criteria.where("email").exists(true));

//   // --- Adding Boolean Conditions
//   Criteria criteria = new Criteria();
//   query.addCriteria(criteria.andOperator(
//     Criteria.where("name").is("paras"),
//     Criteria.where("email").exists(true),
//   ));

//   List<UserEntity> users = mongoTemplate.find(query, UserEntity.class);
// }

// }