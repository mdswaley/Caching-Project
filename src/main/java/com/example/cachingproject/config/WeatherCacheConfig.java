package com.example.cachingproject.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class WeatherCacheConfig {

    @Bean
    public CacheManager weatherCacheManager(RedisConnectionFactory redisConnectionFactory) {
        // Configure Redis cache for weather with 5 minutes TTL
        RedisCacheConfiguration weatherCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .prefixCacheNameWith("weather-") // Prefix for weather cache names
                .entryTtl(Duration.ofMinutes(5)) // Cache TTL for 5 minutes
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())) // String serializer for keys
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())); // JSON serializer for values

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(weatherCacheConfig) // Apply cache config for weather cache
                .build();
    }
}

