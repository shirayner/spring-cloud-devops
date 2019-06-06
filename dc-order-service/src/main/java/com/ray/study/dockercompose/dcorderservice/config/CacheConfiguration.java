package com.ray.study.dockercompose.dcorderservice.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;


/**
 * Spring cache 配置类
 *
 * @author shira 2019/05/13 18:45
 */
@Configuration
@EnableCaching
public class CacheConfiguration {

	/**
	 * 定义 cache key 的生成策略
	 * @return
	 */
	@Bean
	public KeyGenerator caffeineKeyGenerator() {
		// KeyGenerator#generate
		return (target, method, params) -> {
			StringBuilder sb = new StringBuilder();
			sb.append(target.getClass().getName());
			sb.append(method.getName());
			for (Object obj : params) {
				sb.append(obj.toString());
			}
			return sb.toString();
		};
	}


	@Bean
	public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Serializable> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
		template.afterPropertiesSet();
		return template;
	}



	@Bean
	public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		//初始化一个RedisCacheWriter
		RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
		//设置CacheManager的值序列化方式
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

		return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
	}



}

