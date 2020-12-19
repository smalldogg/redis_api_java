package com.redis.api.string;

/**
 * @author wangyh
 * @create 2020-12-12 20:35
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * 位图的一些操作
 */
@Component public class BitOp {

		@Autowired private RedisTemplate<String, Object> redisTemplate;

		/**
		 * 得到今天是今年的第几天,并设置相应的bit位,某天是否登录
		 */
		public void Bit( ) {
				LocalDateTime localDateTime = LocalDateTime.now( );
				int days = localDateTime.getDayOfYear( );
				String id = "10415511";
				redisTemplate.opsForValue( ).setBit( id, days, true );
				Boolean bit = redisTemplate.opsForValue( ).getBit( id, days );
				System.out.println( bit == null ? false : true );
		}


}
