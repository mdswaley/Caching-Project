package com.example.cachingproject.services;

import com.example.cachingproject.dto.WeatherCheck;

public interface WeatherService {
    WeatherCheck getCurrentCityWeather(String city);
}
