//package com.springtest.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//@Component
//@PropertySource(value="classpath:application.yml")
//public class RedisConfig {
//    @Value("${host}")
//    private String host;
//
//    @Value("${port}")
//    private int port;
//
//    @Value("${password}")
//    private String password;
//
//    @Value("${timeout}")
//    private int timeout;
//
//    @Value("${maxIdle}")
//    private int maxIdle;
//
//    @Value("${maxWaitMillis}")
//    private long maxWaitMillis;
//
//    public String getHost() {
//        return host;
//    }
//
//    public void setHost(String host) {
//        this.host = host;
//    }
//
//    public int getPort() {
//        return port;
//    }
//
//    public void setPort(int port) {
//        this.port = port;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public int getTimeout() {
//        return timeout;
//    }
//
//    public void setTimeout(int timeout) {
//        this.timeout = timeout;
//    }
//
//    public int getMaxIdle() {
//        return maxIdle;
//    }
//
//    public void setMaxIdle(int maxIdle) {
//        this.maxIdle = maxIdle;
//    }
//
//    public long getMaxWaitMillis() {
//        return maxWaitMillis;
//    }
//
//    public void setMaxWaitMillis(long maxWaitMillis) {
//        this.maxWaitMillis = maxWaitMillis;
//    }
//
//
//    @Bean
//    public JedisPool redisPoolFactory() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
//        System.out.println(this.toString());
//        return jedisPool;
//    }
//
//    @Override
//    public String toString() {
//        return "RedisConfig{" +
//                "host='" + host + '\'' +
//                ", port=" + port +
//                ", password='" + password + '\'' +
//                ", timeout=" + timeout +
//                ", maxIdle=" + maxIdle +
//                ", maxWaitMillis=" + maxWaitMillis +
//                '}';
//    }
//}
