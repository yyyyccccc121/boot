//package com.springtest.utils;
//
//import com.springtest.config.RedisConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//@Component
//public class RedisUtils {
//
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//    @Autowired
//    private  RedisConfig redisConfig;
//
//    /**
//     * 读取缓存
//     *
//     * @param key
//     * @return
//     */
//    public String get(final String key) {
//        return redisTemplate.opsForValue().get(key);
//    }
//
//    /**
//     * 写入缓存
//     */
//    public boolean set(final String key, String value) {
//        boolean result = false;
//        try {
//            redisTemplate.opsForValue().set(key, value);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 更新缓存
//     */
//    public boolean getAndSet(final String key, String value) {
//        boolean result = false;
//        try {
//            redisTemplate.opsForValue().getAndSet(key, value);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 删除缓存
//     */
//    public boolean delete(final String key) {
//        boolean result = false;
//        try {
//            redisTemplate.delete(key);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 配置一个redis链接池
//     */
//    public  JedisPool getJedisPool(){   JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
// //   RedisConfig redisConfig=new RedisConfig();
//        System.out.println("看看redisConfig有没有初始化成功！" +
//                redisConfig.toString());
//        jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdle());
//        jedisPoolConfig.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getTimeout(), redisConfig.getPassword());
//        System.out.println(this.toString());
//        return jedisPool;
//    }
//}