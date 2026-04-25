package com.example.jounaling_app.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "config_journal_app")
public class ConfigJournalEntity {
  private String key;
  private String value;
}
