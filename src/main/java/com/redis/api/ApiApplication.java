package com.redis.api;

import com.redis.api.hash.RedisHash;
import com.redis.api.set.RedisSet;
import com.redis.api.sorted_set.RedisSset;
import com.redis.api.string.BitOp;
import com.redis.api.string.StringAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication public class ApiApplication {

		public static void main( String[] args ) {
				ConfigurableApplicationContext ctx = SpringApplication.run( ApiApplication.class, args );
				RedisHash bean = ctx.getBean( RedisHash.class );
				bean.redisHash( );
		}

}
