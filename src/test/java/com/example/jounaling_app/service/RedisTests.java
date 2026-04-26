package com.example.jounaling_app.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {
  
  @Autowired
  private RedisTemplate redisTemplate;

  @Test
  void testSetAndGetValue() {
    // Set a value in Redis
    redisTemplate.opsForValue().set("testKey", "testValue");

    // Get the value from Redis
    String value = redisTemplate.opsForValue().get("testKey").toString();

    // Assert that the value is correct
    assert "testValue".equals(value);
  }

}
