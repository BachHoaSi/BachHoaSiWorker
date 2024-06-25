package com.swd391.bachhoasi_worker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Configuration
public class RedisConfig {
    @Value("${redis.url}")
    private String redisUrl;
    @Value("${redis.port}")
    private Integer redisPort;
    private JedisPool pool;

    @PostConstruct
    private void initRedisConnection() {
        pool = new JedisPool(redisUrl, redisPort);
    }
    @Bean
    public Jedis redisResource() {
        return pool.getResource();
    }
}
