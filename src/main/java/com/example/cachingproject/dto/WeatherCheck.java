package com.example.cachingproject.dto;

import lombok.Data;

@Data
public class WeatherCheck {
    private String city;
    private String description;
    private double temperature;
}
