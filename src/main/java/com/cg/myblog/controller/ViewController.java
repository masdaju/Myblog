package com.cg.myblog.controller;

import com.cg.myblog.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Objects;

@Controller
public class ViewController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //登录页面
    @GetMapping("/login")
    public String login(){
        return "login";
    }


    //主页面
    @GetMapping("/homepage")
    public String homepage(){
        return "homepage";
    }
    //添加博客页面
    @GetMapping("/addBolg")
    public String addBolg(){
        return "potsBolg";
    }

    //转跳到注册页面
    @GetMapping("/toRegister")
    public String toRegister(){
        return "register";
    }


    @GetMapping("/logout")
    public  String logout(@RequestHeader("token") String token,HttpSession httpSession){
        if (httpSession!=null){
            httpSession.invalidate();
        }
        //将token从redis里移除
        stringRedisTemplate.delete(token);

        return "redirect:/login";
    }
    @GetMapping("/categories")
    public String categories(){
        Map<String, Object> map = ThreadLocalUtil.get();
        String role = (String) map.get("role");
        if (Objects.equals(role, "user")){
            return "error/401";
        }
        return "tcategory";
    }


    //测试token
    @GetMapping("/someProtectedEndpoint")
    @ResponseBody
    public String someProtectedEndpoint(@RequestHeader("token") String token) {
        System.out.println(token);
        return token;
    }
}
