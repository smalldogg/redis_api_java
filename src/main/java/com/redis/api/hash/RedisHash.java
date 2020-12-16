package com.redis.api.hash;

import com.redis.api.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wangyh
 * @create 2020-12-16 18:01
 */
@Component
public class RedisHash {

		@Autowired private RedisTemplate<String, Object> redisTemplate;

}
