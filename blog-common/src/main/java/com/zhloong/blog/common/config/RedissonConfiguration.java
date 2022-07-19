package com.zhloong.blog.common.config;

import com.zhloong.blog.common.constant.ServiceBusinessConstant;
import com.zhloong.blog.common.util.RedissonUtil;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.NoArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author zhloong
 * @Title: redisson配置
 * @Date 2021/8/13 10:25
 */
@Configuration
@NoArgsConstructor
@ComponentScan
@EnableCaching
public class RedissonConfiguration implements Ordered {

    @Value(value = "${spring.redisson.address}")
    private String host;

    @Value(value = "${spring.redis.password}")
    private String password;

    @Value(value = "${spring.redis.clusterEnable:false}")
    private boolean clusterEnable;

    private RedissonClient redissonClient;

    public RedissonConfiguration(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Bean(destroyMethod = "shutdown")
    RedissonClient redissonInit() {
        Config config = new Config();
        // 判断是否是集群redis节点
        if (clusterEnable) {
            config.useClusterServers().addNodeAddress(host.split(ServiceBusinessConstant.STRING_COMMON));
            // 判断是否有配置密码
            if (StringUtils.isNotBlank(password)) {
                config.useClusterServers().setPassword(password);
            }
        } else {
            config.useSingleServer().setAddress(host);
            // 判断是否有配置密码
            if (StringUtils.isNotBlank(password)) {
                config.useSingleServer().setPassword(password);
            }
        }
        return Redisson.create(config);
    }

    @Bean
    RedissonUtil initRedisManager(RedissonClient redisson) {
        return RedissonUtil.getInstance(redisson);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    /**
     * 配置使用注解的时候缓存配置，默认是序列化反序列化的形式，加上此配置则为 json 形式
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        // 默认5分钟过期
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5));
        // 配置序列化
        RedisCacheConfiguration redisCacheConfiguration =
                config.serializeKeysWith(RedisSerializationContext
                                .SerializationPair
                                .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.
                        SerializationPair.
                        fromSerializer(new GenericJackson2JsonRedisSerializer()));

        return RedisCacheManager.builder(factory).cacheDefaults(redisCacheConfiguration).build();
    }

}
