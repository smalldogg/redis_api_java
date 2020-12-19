package com.redis.api.hash;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.api.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author wangyh
 * @create 2020-12-16 18:01
 */
@Component
public class RedisHash {
		@Autowired
		private ObjectMapper objectMapper;

		@Autowired private RedisTemplate<String, Object> redisTemplate;

		public void redisHash() {
				Person person = new Person();
				person.setName("Tom");
				person.setAge(10);
				String key = "tom";
				Jackson2HashMapper jm = new Jackson2HashMapper(objectMapper, false);
				redisTemplate.opsForHash().putAll(key, jm.toHash(person));
				Map<Object, Object> entries = redisTemplate.opsForHash( ).entries( key );
				entries.entrySet().stream().forEach(kv -> {
						System.out.println(kv.getKey() + ":" + kv.getValue());
				});
		}







}
