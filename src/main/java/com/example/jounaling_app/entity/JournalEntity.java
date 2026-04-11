package com.example.jounaling_app.entity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NonNull;

@Data
@Document(collection = "journals")
public class JournalEntity {

  @Id
  private ObjectId id;

  @NonNull
  private String title;

  private String content;

  @CreatedDate
  private Date date = new Date();
}
