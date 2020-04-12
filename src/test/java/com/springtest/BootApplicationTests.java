package com.springtest;

import com.alibaba.fastjson.JSON;
import com.springtest.model.Message;
import com.springtest.model.staff;
import com.springtest.utils.JedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.*;

import static org.thymeleaf.util.StringUtils.substring;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BootApplicationTests {



    @Test
    public void test1(){
        String content= "YkflskfC";
        if(content.startsWith("Y")&&content.endsWith("C")){
            System.out.println("1111");
            String uuidFileName = substring(content,1,content.length()-1);
            System.out.println(uuidFileName);
        }
    }



    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void contextLoads() {
        logger.trace("这是trace日志");
        logger.debug("这是debug日志");
        logger.info("这是Info日志");
        logger.warn("这是warn日志");
        logger.error("这是error日志");
    }

}
