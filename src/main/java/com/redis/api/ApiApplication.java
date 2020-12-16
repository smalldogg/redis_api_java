package com.redis.api;

import com.redis.api.set.RedisSet;
import com.redis.api.string.BitOp;
import com.redis.api.string.StringAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication public class ApiApplication {

		public static void main( String[] args ) {
				ConfigurableApplicationContext ctx = SpringApplication.run( ApiApplication.class, args );
				RedisSet bean = ctx.getBean( RedisSet.class );
				bean.win2();
		}

}
