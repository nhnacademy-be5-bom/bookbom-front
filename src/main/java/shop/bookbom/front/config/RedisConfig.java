package shop.bookbom.front.config;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;
import shop.bookbom.front.config.dto.KeystoreResponse;

@Configuration
@EnableRedisRepositories
@Import({SecureManagerConfig.class})
@RequiredArgsConstructor
public class RedisConfig {

    private final RestTemplate restTemplate;

    @Value("${keymanager.url}")
    private String keyManagerUrl;

    @Value("${secure.manager.appkey}")
    private String appkey;


    @Value("${secure.manager.redis.host}")
    private String host;

    @Value("${secure.manager.redis.port}")
    private String port;

    @Value("${secure.manager.redis.password}")
    private String password;

    @Value("${spring.redis.database}")
    private String database;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration =
                new RedisStandaloneConfiguration(getValue(host), Integer.parseInt(getValue(port)));
        configuration.setPassword(getValue(password));
        configuration.setDatabase(Integer.parseInt(database));
        return new LettuceConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);

        return redisTemplate;
    }

    private String getValue(String key) {
        String url = keyManagerUrl + appkey + "/secrets/" + key;
        KeystoreResponse response = restTemplate.getForObject(url, KeystoreResponse.class);
        return Objects.requireNonNull(response).getBody().getSecret();
    }
}