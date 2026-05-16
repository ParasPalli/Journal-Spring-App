package com.example.jounaling_app.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.jounaling_app.api_reponse.MailPojo;
import com.example.jounaling_app.service.EmailService;

@Service
public class CronConsumer {
  
  @Autowired
  private EmailService emailService;

  @KafkaListener(topics = "corn-topic", groupId = "corn-group")
  public void sendEmailFromKafka(MailPojo message) {
    // emailService.sendEmail(
    //   message.getTo(),
    //   message.getSubject(), 
    //   message.getBody()
    // );
  }

  @KafkaListener(topics = "corn-topic", groupId = "corn-group")
  public MailPojo receiveMessageFromKafkaCronTopic(MailPojo message) {
    System.out.println("Received message from Kafka topic: " + message.getTo());
    return message;
  }
}
