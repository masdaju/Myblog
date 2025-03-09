//package com.cg.myblog;
//
//import com.cg.myblog.utils.JwtUtil;
//import com.cg.myblog.utils.ThreadLocalUtil;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//
//import java.util.Map;
//@SpringBootTest
//public class ThreadLocalTest {
//@Autowired
//private StringRedisTemplate stringRedisTemplate;
//    @Test
//    public void testThreadLocalSetAndGet(){
//        //提供一个ThreadLocal对象
////        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsicm9sZSI6ImFkbWluIiwiaWQiOjEsInVzZXJuYW1lIjoiYWRtaW4ifSwiZXhwIjoxNzE4MTg2MDA5fQ.HH4x6zJfHRavhohyLLWsKUGOplXAYET6mwXXEWJU7Zk";
////        Map<String, Object> claims = JwtUtil.parseToken(token);
////        ThreadLocalUtil.set(claims);
////        ThreadLocalUtil.remove();
//        Map<String, Object> map = ThreadLocalUtil.get();
////        String username = (String) map.get("role");
//        System.out.println(map);
//    }
//    @Test
//    public void sasda(){
//        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
//        String redisToken = operations.get("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsicm9sZSI6ImFkbWluIiwiaWQiOjEsInVzZXJuYW1lIjoiYWRtaW4ifSwiZXhwIjoxNzE4MTg2MDA5fQ.HH4x6zJfHRavhohyLLWsKUGOplXAYET6mwXXEWJU7Zk");
//        System.out.println(redisToken);
//        Map<String, Object> claims = JwtUtil.parseToken(redisToken);
//        ThreadLocalUtil.set(claims);
//    }
//}
