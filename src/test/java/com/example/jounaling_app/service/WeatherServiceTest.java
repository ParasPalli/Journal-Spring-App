package com.example.jounaling_app.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeatherServiceTest {
  
  @Autowired
  private WeatherService weatherService;

  @Test
  public void testGetWeather() {
    final String city = "London";
    final var weather = weatherService.getWeather(city);
    assert weather != null;
    assert weather.getCityName().equalsIgnoreCase(city);
  }

}
