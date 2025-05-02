package com.example.cachingproject.services.impl;

import com.example.cachingproject.dto.WeatherCheck;
import com.example.cachingproject.services.WeatherService;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherServiceImp implements WeatherService {

    private final WebClient webClient = WebClient.create();

    private final String API_KEY = "69db679c741048fc298a6be934323a5f";
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    @Cacheable(cacheNames = "WEATHER", key = "#city", condition = "#city != null")
    public WeatherCheck getCurrentCityWeather(String city) {
        String url = String.format("%s?q=%s&appid=%s&units=metric", BASE_URL, city, API_KEY);

        String response = webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // synchronous for simplicity

        JSONObject json = new JSONObject(response);

        WeatherCheck weather = new WeatherCheck();
        weather.setCity(json.getString("name"));
        weather.setDescription(json.getJSONArray("weather").getJSONObject(0).getString("description"));
        weather.setTemperature(json.getJSONObject("main").getDouble("temp"));

        return weather;
    }
}
