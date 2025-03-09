package com.cg.myblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cg.myblog.pojo.Users;
import com.cg.myblog.service.UsersService;
import com.cg.myblog.utils.JwtUtil;
import com.cg.myblog.utils.PageBean;
import com.cg.myblog.utils.Result;
import com.cg.myblog.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
@RequestMapping("user")
@Controller
public class UserController {
    @Autowired
    UsersService usersService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @PostMapping("doLogin")
    @ResponseBody
    public Result<String> login(@RequestParam("username") String username, @RequestParam("password") String password,
                                HttpSession session){
        BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();
        LambdaQueryWrapper<Users> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(Users::getUsername,username);
        System.out.println(username);
        if (!usersService.getBaseMapper().exists(wrapper)){
            return Result.error("用户名不存在");
        }
        Users user = usersService.getOne(wrapper);
        if (bCryptPasswordEncoder.matches(password,user.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            //把id，username,和power存储到token
            claims.put("id", user.getId());
            claims.put("role",user.getRole());
            claims.put("username", user.getUsername());
            String token = JwtUtil.genToken(claims);
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token,1, TimeUnit.DAYS);
            session.setAttribute("user", user);
            return Result.success(token);//登录成功
        }
        return Result.error("密码错误");//登录失败

    }
    //分页查询用户
    @GetMapping("/userList")
    public String list(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize
            ,Model model
    ) {
        PageBean<Users> page = usersService.list(pageNum, pageSize);
        Map<String, Object> map = ThreadLocalUtil.get();
        String role = (String) map.get("role");
        if (Objects.equals(role, "user")){
            return "error/401";
        }
        model.addAttribute("page",page);
        return "tuser";

    }

    @PostMapping("deleteUser")//只有管理员能操作
    @ResponseBody
    public Result deleteUser(@RequestParam("id")Integer id ){
        Map<String, Object> map = ThreadLocalUtil.get();
        String role = (String) map.get("role");
        if (role.equals("manager")) {
            return Result.error("你没有权限");
        }
            LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Users::getId, id);
            usersService.remove(wrapper);
            return Result.success();


    }
    //完成测试
    @PostMapping("/register")//注册
    @ResponseBody
    public Result register(@RequestBody Users users){
        LambdaQueryWrapper<Users> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(Users::getUsername,users.getUsername());
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        users.setPassword(encoder.encode(users.getPassword()));
        if (usersService.exists(wrapper)) {
            return Result.error("用户名已经存在");
        }
        usersService.register(users);
        return Result.success();
    }
    //完成测试
    @RequestMapping("selectByName/{username}")
    @ResponseBody
    public String selectByName(@PathVariable String username, Model model){
        LambdaQueryWrapper<Users> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(Users::getUsername,username);
        Users user = usersService.getOne(wrapper);
        model.addAttribute("user",user);
        return "tuser";
    }

}
