package com.redis.api.set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author wangyh
 * @create 2020-12-16 17:13
 */

@Component public class RedisSet {

		@Autowired private RedisTemplate<String, Object> redisTemplate;

		/**
		 * 抽奖，抽过的人不可以再参与
		 * 一共有3论，分别是 1 ，3 ，6 分为 1 2 3等奖
		 */
		public void win( ) {
				List<String> ids = getRandomIds( 10 );
				String key = "ids01";
				redisTemplate.opsForSet( ).add( key, ids.toArray( ) );
				Map<String, List> map = new HashMap<>( );
				for( int i = 1; i <= 3; ++i ) {
						List<String> list = new ArrayList<>( );
						int end = getEnd( i );
						for( int j = 0; j < end; ++j ) {
								list.add( (String) redisTemplate.opsForSet( ).pop( key ) );
						}
						map.put( String.valueOf( i ), list );
				}
				map.entrySet( ).stream( ).forEach( kv -> {
						String win = kv.getKey( );
						List value = kv.getValue( );
						System.out.println( win + "->" + value );
				} );
		}

		/**
		 * 可以重复抽奖
		 */
		public void win2( ) {
				List<String> ids = getRandomIds( 20 );
				String key = "ids02";
				redisTemplate.opsForSet( ).add( key, ids.toArray( ) );
				Map<String, List> map = new HashMap<>( );
				for( int i = 1; i <= 5; ++i ) {
						List<String> list = new ArrayList<>( );
						int end = getEnd( i );
						for( int j = 0; j < end; ++j ) {
								list.add( (String) redisTemplate.opsForSet( ).randomMember( key ) );
						}
						map.put( String.valueOf( i ), list );
				}
				map.entrySet( ).stream( ).forEach( kv -> {
						String win = kv.getKey( );
						List value = kv.getValue( );
						System.out.println( win + "->" + value );
				} );
		}



		private int getEnd( int count ) {
				int end;
				switch( count ) {
						case 1: {
								end = 1;
								break;
						}
						case 2: {
								end = 2;
								break;
						}
						case 3: {
								end = 4;
								break;
						}
						case 4: {
								end = 6;
								break;
						}
						case 5: {
								end = 8;
								break;
						}
						default:
								end = -1;
				}
				return end;
		}

		public List<String> getRandomIds( int capacity ) {
				List<String> ids = new ArrayList<>( capacity );
				Set<String> set = new HashSet<>( );
				Random random = new Random( );
				while( true ) {
						if( set.size( ) == 10 )
								return ids;
						StringBuffer sb = new StringBuffer( );
						while( sb.length( ) != 10 ) {
								int num = random.nextInt( 9 );
								if( num == 0 )
										continue;
								sb.append( num );
						}
						if( !set.contains( sb.toString( ) ) ) {
								set.add( sb.toString( ) );
								ids.add( sb.toString( ) );
						}
				}
		}
}
