package com.bhishma.bookyourshow.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisConfig {

    @Value("${booking.wait-time}")
    private int waitTime;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        //  Redis connection ( host, port)
        return new LettuceConnectionFactory("localhost", 6379);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
    @Bean (name="cacheManager")
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration conf_ready_info = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMillis(50000));

        RedisCacheConfiguration conf_base_info = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMillis(60000));

        RedisCacheConfiguration conf_booking_info=RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMillis(waitTime*60*1000));

        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<String, RedisCacheConfiguration>();

        cacheConfigurations.put("base_info", conf_base_info);

        cacheConfigurations.put("ready_info", conf_ready_info);

        cacheConfigurations.put("BookingResponse",conf_booking_info);

        cacheConfigurations.put("CheckStatus",conf_booking_info);


        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(connectionFactory)
                .withInitialCacheConfigurations(cacheConfigurations).build();
    }
}

