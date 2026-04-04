package com.example.jounaling_app.controller;

import java.util.*;

import org.springframework.web.bind.annotation.RestController;

import com.example.jounaling_app.entity.JournalEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {
  private Map<Integer, JournalEntity> journalEntries = new HashMap<>();
  
  @GetMapping()
  public List<JournalEntity> getAllEntries() {
    return new ArrayList<>(journalEntries.values());
  }

  @PostMapping()
  public JournalEntity createEntry(@RequestBody JournalEntity entry) {
    journalEntries.put(entry.getId(), entry);
    return entry;
  }

  @GetMapping("/{id}")
  public JournalEntity getEntryById(@PathVariable int id) {
    return journalEntries.get(id);
  }

  @DeleteMapping("/{id}")
  public JournalEntity deleteEntry(@PathVariable int id) {
    return journalEntries.remove(id); 
  }
}
