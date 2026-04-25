package com.example.jounaling_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.jounaling_app.api_reponse.WeatherResponsePojo;
import com.example.jounaling_app.cache.AppCache;
import com.example.jounaling_app.enums.KeyEnum;

@Component
public class WeatherService {
  
  private static final String API_KEY = "YOUR_API_KEY";

  @Autowired
  private AppCache appCache;

  @Autowired
  private RestTemplate restTemplate;
  
  public WeatherResponsePojo getWeather(String city) {
    // String apiUri = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", city, API_KEY);

    String apiUri = String.format(appCache.cache.get(KeyEnum.WEATHER_URL.name()), city, API_KEY);
    ResponseEntity<WeatherResponsePojo> response = restTemplate.exchange(apiUri, HttpMethod.GET, null, WeatherResponsePojo.class);
    // response.getStatusCode();
    return response.getBody();
  }

}
