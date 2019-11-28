package com.metrix.results.config;
import com.metrix.libs.model.ActivityRuleEvaluationResults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;



@Configuration
@EnableScheduling
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String redisHostname;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisHostname);
        configuration.setPort(redisPort);
        JedisConnectionFactory factory = new JedisConnectionFactory(configuration);
        return factory;
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("evalresultstopic");
    }

    @Bean
    public RedisTemplate<String, ActivityRuleEvaluationResults> redisTemplate() {
        final RedisTemplate<String, ActivityRuleEvaluationResults> template = new RedisTemplate<String, ActivityRuleEvaluationResults>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<ActivityRuleEvaluationResults>(ActivityRuleEvaluationResults.class));
        return template;
    }
}
