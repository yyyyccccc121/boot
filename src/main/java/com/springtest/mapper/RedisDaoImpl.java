//package com.springtest.mapper;
//
//import com.springtest.service.RedisDataSource;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import redis.clients.jedis.Jedis;
//
//
//@Repository
//public class RedisDaoImpl implements RedisDao{
//
//    private static final Logger logger = LoggerFactory.getLogger(RedisDaoImpl.class);
//
//    @Autowired
//    private RedisDataSource redisDataSource;
//
//    @Override
//    public void putInfo2Redis(String str){
//        String keyName = "FirstInfo";
//        String fieldName = "redisDemo";
//        boolean broken = false;
//        Jedis shardedJedis = redisDataSource.getRedisClient();
//        if (shardedJedis == null) {
//            System.out.println("shardedJedis为空？？？？？？？？");
//            return;
//        }
//        try {
//            System.out.println("执行set方法");
//            shardedJedis.hset(keyName,fieldName, str);
//        } catch (Exception e) {
//            broken = true;
//           redisDataSource.returnBrokenResource(shardedJedis);
//
//        } finally {
//            redisDataSource.returnResource(shardedJedis, broken);
//        }
//    }
//}
