package com.swd391.bachhoasi_worker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Configuration
public class RedisConfig {
    @Value("${redis.url}")
    private String redisUrl;
    @Value("${redis.port}")
    private Integer redisPort;
    private JedisPool pool;

    @EventListener
    private void initRedisConnection(ApplicationReadyEvent event) {
        pool = new JedisPool(redisUrl, redisPort);
    }
    @Bean
    public Jedis redisResource() {
        return pool.getResource();
    }
}
