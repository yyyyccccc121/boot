//package com.springtest.service.serviceimpl;
//
//import com.springtest.config.RedisConfig;
//import com.springtest.service.RedisDataSource;
//import com.springtest.utils.RedisUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//@Repository
//public class RedisDataSourceImpl implements RedisDataSource {
//    private static final Logger logger = LoggerFactory.getLogger(RedisDataSourceImpl.class);
//
////    @Autowired
////    private JedisPool jedisPool;
//
//      @Autowired
//      private  RedisConfig redisConfig;
//    @Override
//    public Jedis getRedisClient() {
//        try {
//            JedisPool jedisPool = null;
//            try {
//                System.out.println("让我们看看config有没有读到值" +redisConfig.toString());
//                jedisPool=redisConfig.redisPoolFactory();
//            } catch (Exception e) {
//                System.out.println("初始化 jedis连接池失败！！！请求支援" );
//                e.printStackTrace();
//            }
//            return jedisPool.getResource();
//        } catch (Exception e) {
//            logger.error("获取RedisClient异常：" + e);
//        }
//        return null;
//
//
//    }
//
//
//    @Override
//    public void returnResource(Jedis jedis, boolean broken) {
//        if (broken) {
//            if(jedis!=null){
//                jedis.close();
//            }
//        } else {
//            if(jedis!=null){
//                jedis.close();
//            }
//        }
//        if(broken){
//            try{
//
////                 jedis = jedisPool.getResource();
//           }
//            catch (Exception e){
//                logger.error("jedis 设置异常：" + e);
//
//            }
//        }
//
//
//    }
//
//    @Override
//    public synchronized void returnBrokenResource(Jedis jedis) {
//        if (jedis != null) {
////            jedisPool.returnBrokenResource(jedis);
//
//        }
//    }
//}
