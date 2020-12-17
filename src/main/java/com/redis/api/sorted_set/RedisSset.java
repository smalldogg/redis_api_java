package com.redis.api.sorted_set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author wangyh
 * @create 2020-12-17 17:32
 */
@Component
public class RedisSset {

		@Autowired private RedisTemplate<String, Object> redisTemplate;

		public void SortedSet() {
				String key = "scores";
				Map<String, Double> randomScoresAndID = getRandomScoresAndID( 10 );
				randomScoresAndID.entrySet().stream().forEach( kv -> {
						redisTemplate.opsForZSet().add(key, kv.getKey(), kv.getValue());
				});
				Set<ZSetOperations.TypedTuple<Object>> typedTuples = redisTemplate.opsForZSet( ).rangeWithScores( key, 0, -1 );
				typedTuples.stream().forEach(x -> {
						System.out.println(x.getValue()  + "->" + x.getScore());
				});
		}

		public Map<String, Double> getRandomScoresAndID(int capacity) {
				Map<String, Double> map = new HashMap<>();
				int base = 100;
				Random random =  new Random();
				double score;
				while (true) {
						if (map.size() == capacity) return map;
						StringBuffer id = new StringBuffer();
						score = base - random.nextInt( 20);
						while (id.length() != 10) {
								int num = random.nextInt(10);
								if (num == 0 && id.length() == 0) continue;
								id.append(num);
						}
						map.put(id.toString(), score);
				}
		}
}
