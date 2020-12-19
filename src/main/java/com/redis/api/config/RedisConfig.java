package com.redis.api.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author wangyh
 * @create 2020-12-13 9:15
 */
@Configuration public class RedisConfig {

		@Autowired private RedisConnectionFactory connectionFactory;

		@Bean public RedisTemplate<String, Object> redisTemplate( ) {
				RedisTemplate<String, Object> template = new RedisTemplate( );
				template.setConnectionFactory( connectionFactory );
				Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>( Object.class );
				ObjectMapper om = new ObjectMapper( );
				om.setVisibility( PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY );
				om.enableDefaultTyping( ObjectMapper.DefaultTyping.NON_FINAL );
				jackson2JsonRedisSerializer.setObjectMapper( om );
				//设置key的序列化类型
				StringRedisSerializer stringRedisSerializer = new StringRedisSerializer( );
				template.setKeySerializer( stringRedisSerializer );
				template.setValueSerializer( jackson2JsonRedisSerializer );
				//设置hash类型的序列化类型
				template.setHashKeySerializer( jackson2JsonRedisSerializer );
				template.setHashValueSerializer( jackson2JsonRedisSerializer );
				return template;
		}

}
