package com.example.jounaling_app.crons;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.jounaling_app.entity.UserEntity;
import com.example.jounaling_app.service.EmailService;
import com.example.jounaling_app.service.UserService;

@Component
public class UserCron {
  
  @Autowired
  private EmailService emailService;

  @Autowired
  private UserService userService;

  public List<UserEntity> fetchUsersWithEmails() {
    final List<UserEntity> users = userService.getAllUsers().stream().filter(user -> user.getEmail() != null).toList();
    return users;
  }


  @Scheduled(cron = "0 0 10 * * MON") // Every Monday at 10 AM
  public void sendEveryWeek() {
    final List<UserEntity> users = fetchUsersWithEmails();
    for (UserEntity user : users) {
      emailService.sendEmail(
        user.getEmail(),
        "Weekly Journal Reminder", 
        "Don't forget to write in your journal this week!"
      );
    }
  }

  @Scheduled(cron = "0 * * * * *") // Every minute
  public String sendEveryMinute() {
    return "Working";
  }
}
