package com.example.jounaling_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.jounaling_app.entity.ConfigJournalEntity;

public interface ConfigJournalRepo extends MongoRepository<ConfigJournalEntity, String> {
  ConfigJournalEntity findByKey(String key);
}