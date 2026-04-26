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

  @Autowired
  private RedisService redisService;
  
  public WeatherResponsePojo getWeather(String city) {
    // If Cached data is present in Redis, return that
    WeatherResponsePojo cachedWeather = redisService.getValue("weather_" + city, WeatherResponsePojo.class);
    if (cachedWeather != null) return cachedWeather;

    //
    // String apiUri = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", city, API_KEY);

    String apiUri = String.format(appCache.cache.get(KeyEnum.WEATHER_URL.name()), city, API_KEY);
    ResponseEntity<WeatherResponsePojo> response = restTemplate.exchange(apiUri, HttpMethod.GET, null, WeatherResponsePojo.class);

    final WeatherResponsePojo weather = response.getBody();

    // For Testing
    // final WeatherResponsePojo weather = WeatherResponsePojo.builder()
    //   .cityName(city)
    //   .temperature(25.0)
    //   .description("Clear sky")
    //   .build();
    
    if (weather != null) {
      redisService.setValue("weather_" + city, weather, 600L); // 10 minutes (600 seconds)
    }
    return weather;
  }
}
