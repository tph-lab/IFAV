package com.yc.ifav.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisClient {
	private Jedis jedis;// 闈炲垏鐗囧鎴风杩炴帴
	private JedisPool jedisPool;// 闈炲垏鐗囪繛鎺ユ睜
	private ShardedJedis shardedJedis;// 鍒囩墖瀹㈡埛绔繛鎺�
	private ShardedJedisPool shardedJedisPool;// 鍒囩墖杩炴帴姹�
	
	private static RedisClient client;
	
	public Jedis getJedis() {
		return jedis;
	}

	public ShardedJedis getShardedJedis() {
		return shardedJedis;
	}

	public static RedisClient getInstance(){
		if(client==null){
			client=new RedisClient();
		}
		return client;
	}

	private RedisClient() {
		if(   shardedJedisPool==null){
			initialShardedPool();
			shardedJedis = shardedJedisPool.getResource();
		}
		if( jedis==null){
			initialPool();
			jedis = jedisPool.getResource();
			//jedis.auth("123456");//设置密码
		}
	}

	/**
	 * 鍒濆鍖栭潪鍒囩墖姹�
	 */
	private void initialPool() {
		// 姹犲熀鏈厤缃�
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setTestOnBorrow(false);
		jedisPool = new JedisPool(config, "127.0.0.1", 6379);
	}

	/**
	 * 鍒濆鍖栧垏鐗囨睜
	 */
	private void initialShardedPool() {
		// 姹犲熀鏈厤缃�
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setTestOnBorrow(false);//淇濊瘉姣忔鑾峰彇鏈夋晥鐨勮繛鎺ュ璞�  true 姣忔鑾峰彇杩炴帴鐨勬椂鍊欓渶瑕佹暟鎹簱楠岃瘉鏈夋晥鎬э紝鎵�浠ュ湪楂樺苟鍙戞儏鍐典笅鎬ц兘闄嶄綆
		// slave閾炬帴
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo("127.0.0.1", 6379, "master"));
		// 鏋勯�犳睜
		shardedJedisPool = new ShardedJedisPool(config, shards);
	}
	
	
	
	
	 
}
