package com.example.jounaling_app.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jounaling_app.entity.ConfigJournalEntity;
import com.example.jounaling_app.repository.ConfigJournalRepo;

import jakarta.annotation.PostConstruct;

@Component
public class AppCache {

  @Autowired
  private ConfigJournalRepo configJournalRepo;

  public Map<String, String> cache = new HashMap<>();

  @PostConstruct
  public void init() {
    cache.clear();
    final List<ConfigJournalEntity> configEntries = configJournalRepo.findAll();
    configEntries.forEach(config -> {
      cache.put(config.getKey(), config.getValue());
    });
  }
}
