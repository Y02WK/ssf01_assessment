package ssf.ibf.booksearch.config;

import static ssf.ibf.booksearch.constants.Constants.REDIS_TEMPLATE;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Optional<Integer> redisPort;

    @Value("#{systemEnvironment['REDIS_PW']}")
    private String redisPassword;

    @Bean(REDIS_TEMPLATE)
    public RedisTemplate<String, String> createRedisTemplate() {
        // configuring the database
        final RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(redisHost);
        redisConfig.setPort(redisPort.get());
        if (redisPassword != null) {
            redisConfig.setPassword(redisPassword);
        }

        // create client and factory
        final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder()
                .build();
        final JedisConnectionFactory jedisFac = new JedisConnectionFactory(redisConfig, jedisClient);
        jedisFac.afterPropertiesSet();

        final RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisFac);
        template.setKeySerializer(new StringRedisSerializer()); // keys in utf-8
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }
}
