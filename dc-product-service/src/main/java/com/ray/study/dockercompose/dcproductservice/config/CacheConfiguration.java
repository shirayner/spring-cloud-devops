package com.ray.study.dockercompose.dcproductservice.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Spring cache 配置类
 *
 * @author shira 2019/05/13 18:45
 */
@Configuration
@EnableCaching
public class CacheConfiguration {

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



}
