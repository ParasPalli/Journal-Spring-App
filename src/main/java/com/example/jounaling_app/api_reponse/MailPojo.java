package com.example.jounaling_app.api_reponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailPojo {
  private String to;
  private String subject;
  private String body;
}
