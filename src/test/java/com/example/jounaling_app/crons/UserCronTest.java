package com.example.jounaling_app.crons;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserCronTest {
  
  @Autowired
  private UserCron userCron;

  @Test
  public void testCronWorkingEveryMinute() {
    final String result = userCron.sendEveryMinute();
    assert "Working".equals(result);
  }
}
