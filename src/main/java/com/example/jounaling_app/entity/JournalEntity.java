package com.example.jounaling_app.entity;

public class JournalEntity {
  private int id;
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  
  private String title;
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }

  private String content;
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
}
