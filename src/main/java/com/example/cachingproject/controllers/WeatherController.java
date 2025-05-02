package com.example.cachingproject.controllers;

import com.example.cachingproject.dto.WeatherCheck;
import com.example.cachingproject.services.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/api/weather/{city}")
    public ResponseEntity<WeatherCheck> getWeather(@PathVariable String city) {
        System.out.println("Fetching weather for city: " + city); // Debugging log
        return new ResponseEntity<>(weatherService.getCurrentCityWeather(city), HttpStatus.OK);
    }
}
