package com.example.demo;

import com.datacanvas.aps.pipes.service.common.RequestMapObject;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisShardInfo;

public class RedisTest {

    public static void main(String[] args) {

        String host = "redis.aps.com";
        int port = 31754;
        String password = "123456";

//        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration();
//        sentinelConfig.setMaster("mymaster");
//        RedisNode redisNode = new RedisNode("172.20.51.5", 31754);
//        List<RedisNode> sentinels = new LinkedList<>();
//        sentinels.add(redisNode);
//        sentinelConfig.setSentinels(sentinels);

//        JedisConnectionFactory factory = new JedisConnectionFactory(sentinelConfig);
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setDatabase(2);
        JedisShardInfo jedisShardInfo = new JedisShardInfo(host, port);
        jedisShardInfo.setPassword(password);
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        factory.setShardInfo(jedisShardInfo);

        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer(Object.class));
        redisTemplate.afterPropertiesSet();
        RequestMapObject obj = (RequestMapObject)redisTemplate.opsForValue().get("SocketIoMapCache:11470808-261e-4b23-9680-f6b6a5b82bb0");
        System.out.println(obj);
    }
}
