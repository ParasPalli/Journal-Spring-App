package com.example.jounaling_app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jounaling_app.entity.JournalEntity;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerMongo {
  
  @GetMapping()
  public List<JournalEntity> getAllEntries() {
    return null;
  }

  @PostMapping()
  public JournalEntity createEntry(@RequestBody JournalEntity entry) {
    return null;
  }

  @GetMapping("/{id}")
  public JournalEntity getEntryById(@PathVariable int id) {
    return null;
  }

  @DeleteMapping("/{id}")
  public JournalEntity deleteEntry(@PathVariable int id) {
    return null;
  }
}
