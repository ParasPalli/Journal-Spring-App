package com.example.jounaling_app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.jounaling_app.entity.JournalEntity;

public interface JournalEntryRepo extends MongoRepository<JournalEntity, ObjectId>{

}
