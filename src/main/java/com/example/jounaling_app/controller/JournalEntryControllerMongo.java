package com.example.jounaling_app.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jounaling_app.entity.JournalEntity;
import com.example.jounaling_app.entity.UserEntity;
import com.example.jounaling_app.service.JournalEntryService;
import com.example.jounaling_app.service.UserService;



@RestController
@RequestMapping("/journal")
public class JournalEntryControllerMongo {

  @Autowired
  private JournalEntryService journalEntryService;

  @Autowired
  private UserService userService;
  
  @GetMapping()
  public ResponseEntity<List<JournalEntity>> getEntriesByUser() {
    final UserEntity user = userService.getCurrentUser();
    if (user != null) {
      List<JournalEntity> entries = user.getJournalEntries();
      return ResponseEntity.ok(entries);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
  
  @PostMapping()
  public ResponseEntity<?> createEntry(@RequestBody JournalEntity entry) {
    try {
      final UserEntity user = userService.getCurrentUser();
      if (user == null) return ResponseEntity.notFound().build();

      JournalEntity createdEntry = journalEntryService.saveEntry(entry, user);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdEntry);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<JournalEntity> getEntryById(@PathVariable ObjectId id) {
    final Optional<JournalEntity> entry = journalEntryService.getEntryById(id);
    if (entry.isPresent()) {
      return ResponseEntity.ok(entry.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<JournalEntity> deleteEntryById(@PathVariable ObjectId id) {
    final Optional<JournalEntity> entry = journalEntryService.deleteById(id);
    if (entry.isPresent()) {
      return ResponseEntity.ok(entry.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<JournalEntity> updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntity updatedEntry) {
    final Optional<JournalEntity> entry = journalEntryService.updateEntry(id, updatedEntry);
    return entry.map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }
}