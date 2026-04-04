package com.example.jounaling_app.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jounaling_app.entity.JournalEntity;
import com.example.jounaling_app.repository.JournalEntryRepo;

@Component
public class JournalEntryService {
  
  @Autowired
  private JournalEntryRepo journalEntryRepo;

  public JournalEntity saveEntry(JournalEntity entry) {
    return journalEntryRepo.save(entry);
  }

  public List<JournalEntity> getAllEntries() {
    return journalEntryRepo.findAll();
  }

  public Optional<JournalEntity> getEntryById(ObjectId id) {
    return journalEntryRepo.findById(id);
  }

  public Optional<JournalEntity> deleteById(ObjectId id) {
    Optional<JournalEntity> entry = journalEntryRepo.findById(id);
    entry.ifPresent(e -> journalEntryRepo.deleteById(id));
    return entry;
  }

  public JournalEntity updateEntry(ObjectId id, JournalEntity updatedEntry) {
    return journalEntryRepo.findById(id)
        .map(entry -> {
          if (entry != null) {
            entry.setTitle((updatedEntry.getTitle() == null || updatedEntry.getTitle().isEmpty()) ? entry.getTitle() : updatedEntry.getTitle());
            entry.setContent((updatedEntry.getContent() == null || updatedEntry.getContent().isEmpty()) ? entry.getContent() : updatedEntry.getContent());
            return journalEntryRepo.save(entry);
          }
          return null;
        }).orElse(null); 
  }
}
