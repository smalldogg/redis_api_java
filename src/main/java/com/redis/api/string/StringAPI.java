package com.redis.api.string;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wangyh
 * @create 2020-12-16 14:35
 */

@Component public class StringAPI {

		@Autowired private RedisTemplate<String, Object> redisTemplate;

		public void stringApi( ) {
				redisTemplate.opsForValue( ).set( "hello", "中" );
		}
}
