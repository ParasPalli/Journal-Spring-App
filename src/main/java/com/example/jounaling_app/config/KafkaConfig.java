package com.example.jounaling_app.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
  
  @Bean
  public NewTopic cornTopic() {
    return TopicBuilder.name("corn-topic").partitions(2).build();
  } 

}
