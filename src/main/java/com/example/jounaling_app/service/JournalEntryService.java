package com.example.jounaling_app.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.jounaling_app.entity.JournalEntity;
import com.example.jounaling_app.entity.UserEntity;
import com.example.jounaling_app.repository.JournalEntryRepo;

@Component
public class JournalEntryService {
  
  @Autowired
  private JournalEntryRepo journalEntryRepo;

  @Autowired
  private UserService userService;

  @Transactional
  public JournalEntity saveEntry(JournalEntity entry, UserEntity user) {
    final UserEntity existingUser = userService.findByUsername(user.getUsername());
    final JournalEntity savedEntry = journalEntryRepo.save(entry);

    existingUser.getJournalEntries().add(savedEntry);
    userService.justSaveUser(existingUser);
    return savedEntry;
  }

  public List<JournalEntity> getAllEntries() {
    return journalEntryRepo.findAll();
  }

  public Optional<JournalEntity> getEntryById(ObjectId id) {
    final UserEntity user = userService.getCurrentUser();
    if (user == null) return Optional.empty();
    
    return user.getJournalEntries().stream().filter(entry -> entry.getId().equals(id)).findFirst();
  }

  public Optional<JournalEntity> updateEntry(ObjectId id, JournalEntity updatedEntry) {
    final UserEntity user = userService.getCurrentUser();
    if (user == null) return Optional.empty();

    final Optional<JournalEntity> existingEntry = user.getJournalEntries().stream().filter(entry -> entry.getId().equals(id)).findFirst();
    if (!existingEntry.isPresent()) return Optional.empty();

    return existingEntry.map(entry -> {
      if (entry != null) {
        entry.setTitle((updatedEntry.getTitle() == null || updatedEntry.getTitle().isEmpty()) ? entry.getTitle() : updatedEntry.getTitle());
        entry.setContent((updatedEntry.getContent() == null || updatedEntry.getContent().isEmpty()) ? entry.getContent() : updatedEntry.getContent());
        return journalEntryRepo.save(entry);
      }
      return null;
    }); 
  }

  public Optional<JournalEntity> deleteById(ObjectId id) {
    final UserEntity user = userService.getCurrentUser();
    if (user == null) return Optional.empty();

    Optional<JournalEntity> entry = journalEntryRepo.findById(id);
    if (entry.isPresent()) {
      if (user.getJournalEntries().removeIf((JournalEntity j) -> j.getId().equals(id))) {
        journalEntryRepo.deleteById(id);
        userService.justSaveUser(user);
        return entry;
      }
    }
    return Optional.empty();
  }
}
