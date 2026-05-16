package com.example.jounaling_app.crons;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.jounaling_app.api_reponse.MailPojo;
import com.example.jounaling_app.entity.UserEntity;
import com.example.jounaling_app.service.UserService;

@Component
public class UserCron {

  @Autowired
  private UserService userService;

  @Autowired
  private KafkaTemplate<String, MailPojo> kafkaTemplate;

  public List<UserEntity> fetchUsersWithEmails() {
    final List<UserEntity> users = userService.getAllUsers().stream().filter(user -> user.getEmail() != null).toList();
    return users;
  }


  @Scheduled(cron = "0 0 10 * * MON") // Every Monday at 10 AM
  public void sendEveryWeek() {
    final List<UserEntity> users = fetchUsersWithEmails();
    for (UserEntity user : users) {
      // ---- Send a message to Kafka topic
      // ---- Acts as Producer
      final MailPojo mailPojo = MailPojo.builder()
        .to(user.getEmail())
        .subject("Weekly Journal Reminder")
        .body("Don't forget to write in your journal this week!")
        .build();


      //  (String topic, String key (Same Key, Same Partition), @Nullable MailPojo data)
      kafkaTemplate.send("corn-topic", mailPojo.getTo(), mailPojo);


      // --- Not Directly
      // emailService.sendEmail(
      //   user.getEmail(),
      //   "Weekly Journal Reminder", 
      //   "Don't forget to write in your journal this week!"
      // );
    }
  }

  @Scheduled(cron = "0 * * * * *") // Every minute
  public String sendEveryMinute() {
    return "Working";
  }

  public void sendMessageToKafkaCornTopic(MailPojo mailPojo) {
    kafkaTemplate.send("corn-topic", mailPojo.getTo(), mailPojo).thenAccept(result -> {
      System.out.println("Message sent to Kafka topic: " + mailPojo.getTo());
    }).exceptionally(ex -> {
      System.err.println("Failed to send message to Kafka topic: " + ex.getMessage());
      return null;
    });
  }
}
