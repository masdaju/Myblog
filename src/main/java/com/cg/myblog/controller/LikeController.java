package com.cg.myblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cg.myblog.pojo.Likes;
import com.cg.myblog.pojo.Posts;
import com.cg.myblog.service.LikesService;
import com.cg.myblog.utils.Result;
import com.cg.myblog.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class LikeController {
    @Autowired
    LikesService likesService;
    @PostMapping("addToLikes")
    @ResponseBody
    public Result add(@RequestParam("pid") Integer pid){
        Map<Object,Object> map = ThreadLocalUtil.get();
        Integer uid = (Integer) map.get("id");
        Likes likes =new Likes(uid,pid);
        LambdaQueryWrapper<Likes> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(Likes::getPostId,pid)
                        .eq(Likes::getUserId,uid);
        if (likesService.exists(wrapper)) {
            return Result.error("你已经收藏过了");
        }
        likesService.save(likes);
        return Result.success();
    }
    @PostMapping("deleteLikes")
    @ResponseBody
    public Result delete(@RequestParam("lid") Integer lid){
        LambdaQueryWrapper<Likes> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(Likes::getId,lid);
        likesService.remove(wrapper);
        return Result.success();
    }

    @GetMapping("getmyLikes")//获取喜欢列表
    public String getMyLikes(Model model){
        Map<Object,Object> map = ThreadLocalUtil.get();
        Integer uid = (Integer) map.get("id");
        List<Likes> likes=likesService.getlist(uid);
        model.addAttribute("likes",likes);
        return "tlikes";
    }
}
