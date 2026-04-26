package com.example.jounaling_app.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RedisService {
  
  @Autowired
  private RedisTemplate redisTemplate;

  public void setValue(String key, Object entityClass, Long expiry) {
    try {
      final ObjectMapper objectMapper = new ObjectMapper();
      final String jsonValue = objectMapper.writeValueAsString(entityClass);

      redisTemplate.opsForValue().set(key, jsonValue, expiry, TimeUnit.SECONDS);
    } catch (Exception e) {
      log.error("Error while setting value in Redis for key: " + key, e);
    }
  }

  public <T> T getValue(String key, Class<T> entityClass) {
    try {
      final Object o = redisTemplate.opsForValue().get(key);
      final ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(o.toString(), entityClass);
    } catch (Exception e) {
      log.error("Error while fetching value from Redis for key: " + key, e);
      return null;
    }
  }
}
