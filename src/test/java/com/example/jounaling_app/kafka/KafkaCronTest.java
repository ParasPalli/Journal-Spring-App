package com.example.jounaling_app.kafka;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jounaling_app.api_reponse.MailPojo;
import com.example.jounaling_app.crons.UserCron;
import com.example.jounaling_app.service.kafka.CronConsumer;

@SpringBootTest
public class KafkaCronTest {
  
  @Autowired
  private UserCron userCron;

  @Autowired
  private CronConsumer cronConsumer;

  @Test
  public void testKafkaCronTopic() {
    MailPojo mailPojo = MailPojo.builder().to("test@example.com").subject("Test").body("Test message").build();

    userCron.sendMessageToKafkaCornTopic(mailPojo);

    verify(cronConsumer,timeout(10000)).receiveMessageFromKafkaCronTopic(any(MailPojo.class));
  }

}
