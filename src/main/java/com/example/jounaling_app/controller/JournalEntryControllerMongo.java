package com.example.jounaling_app.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jounaling_app.entity.JournalEntity;
import com.example.jounaling_app.service.JournalEntryService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/journal")
public class JournalEntryControllerMongo {

  @Autowired
  private JournalEntryService journalEntryService;
  
  @GetMapping()
  public List<JournalEntity> getAllEntries() {
    return journalEntryService.getAllEntries();
  }

  @PostMapping()
  public JournalEntity createEntry(@RequestBody JournalEntity entry) {
    return journalEntryService.saveEntry(entry);
  }

  @GetMapping("/{id}")
  public JournalEntity getEntryById(@PathVariable ObjectId id) {
    return journalEntryService.getEntryById(id).orElse(null);
  }

  @DeleteMapping("/{id}")
  public JournalEntity deleteEntryById(@PathVariable ObjectId id) {
    return journalEntryService.deleteById(id).orElse(null);
  }

  @PutMapping("/{id}")
  public JournalEntity updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntity updatedEntry) {
    return journalEntryService.updateEntry(id, updatedEntry);
  }
}
