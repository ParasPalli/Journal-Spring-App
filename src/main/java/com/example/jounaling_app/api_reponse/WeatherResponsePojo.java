package com.example.jounaling_app.api_reponse;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WeatherResponsePojo {

  // if vairable is in camelCase and json property is in snake_case,
  // we can use @JsonProperty to map them together
  @JsonProperty("city_name")
  private String cityName;

  private String description;
  private double temperature;
  private int humidity;
  private double windSpeed;  
}
