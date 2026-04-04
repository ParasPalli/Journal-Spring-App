package com.example.jounaling_app.entity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "journals")
public class JournalEntity {

  @Id
  private ObjectId id;

  private String title;

  private String content;

  @CreatedDate
  private Date date = new Date();
  
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  
  public ObjectId getId() {
    return id;
  }
  public void setId(ObjectId id) {
    this.id = id;
  }
  
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }


  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
}
