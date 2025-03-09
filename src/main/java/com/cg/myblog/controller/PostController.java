package com.cg.myblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cg.myblog.pojo.Posts;
import com.cg.myblog.service.PostsService;
import com.cg.myblog.utils.PageBean;
import com.cg.myblog.utils.Result;
import com.cg.myblog.utils.ThreadLocalUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class PostController {
    @Autowired
    PostsService postsService;
    //完成测试
    @PostMapping("addPost")
    @ResponseBody
    public Result addPost(@RequestBody Posts posts){
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer uid = (Integer) map.get("id");
        posts.setUserId(uid);
        postsService.save(posts);
        return Result.success();
    }
    //完成测试
    @PostMapping("deletePost")//自己和管理员可以操作
    @ResponseBody              //文章id                         //用户id
    public Result deletePost(@RequestParam("id") Integer id,@RequestParam("uid") Integer uid){
        LambdaQueryWrapper<Posts> wrapper =new LambdaQueryWrapper<>();
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer uid1 = (Integer) map.get("id");
        String role = (String) map.get("role");
        if(Objects.equals(uid, uid1) || Objects.equals(role, "admin")) {
            wrapper.eq(Posts::getId, id);
            postsService.remove(wrapper);
            return Result.success();
        }
        return Result.error("这不是你的文章");
    }
    //完成测试
    @PostMapping("updatePost")//更新文章
    @ResponseBody
    public Result updatePost(@RequestBody Posts posts){
        System.out.println(posts);
        LambdaUpdateWrapper<Posts> wrapper =new LambdaUpdateWrapper<>();//后续同Localhost的用户数据进行对比后在执行
        wrapper.eq(Posts::getId,posts.getId())
                .set(Posts::getTitle,posts.getTitle())
                .set(Posts::getContent,posts.getContent())
                .set(Posts::getStatus,posts.getStatus());

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");


        if (!Objects.equals(id, posts.getUserId())){
            return Result.error("不要修改别人的文章");
        }
        postsService.update(wrapper);
        return Result.success();
    }

    //完成测试
    @GetMapping("getPostforAll")//展示已经发布且审核后的文章
    public String getPostForAll(Model model){
        PageBean<Posts> postsPageBean =new PageBean<>();
        LambdaQueryWrapper<Posts> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(Posts::getStatus,"published");
        List<Posts> posts=postsService.list(wrapper);
        postsPageBean.setItems(posts);
        model.addAttribute("postsPageBean",postsPageBean);
        return "tpost";
    }
    //draft草稿，review已发布未审核，published已发布已审核
    //完成测试
    @GetMapping("getPostforManager")//给管理员展示未发布的文章
    public String getPostForManager(Model model){
        PageBean<Posts> postsPageBean =new PageBean<>();
        LambdaQueryWrapper<Posts> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(Posts::getStatus,"review");
        Map<Object,Object> map =ThreadLocalUtil.get();
        String role = (String) map.get("role");
        if (role.equals("user")){

           return "error/401";
        }
        List<Posts> posts=postsService.list(wrapper);
        postsPageBean.setItems(posts);
        model.addAttribute("postsPageBean",postsPageBean);
        return "tpost";
    }
    //完成测试
    @PostMapping("changeStatus")//审核文章
    @ResponseBody
     public Result changeStatus(@RequestParam("pid")Integer pid){
        LambdaUpdateWrapper<Posts> wrapper =new LambdaUpdateWrapper<>();
        wrapper.eq(Posts::getId,pid)
                        .set(Posts::getStatus,"published");
        Map<String, Object> map = ThreadLocalUtil.get();
        String role = (String) map.get("role");
        if (Objects.equals(role, "user")){
            return Result.error("没有权限审核文章");
        }
        postsService.update(wrapper);

        return Result.success();
    }

    @GetMapping("Postdetail/{id}")//获取文章详情并增加访问量
    public String PostDetail(@PathVariable("id") Integer id, Model model){
        LambdaQueryWrapper<Posts> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(Posts::getId,id);

        //增加访问量
            LambdaUpdateWrapper<Posts> wrapper1 =new LambdaUpdateWrapper<>();
            wrapper1.setSql("view_count=view_count+1")
                            .eq(Posts::getId,id);
            postsService.update(wrapper1);
        Posts posts = postsService.getOne(wrapper);
            model.addAttribute("posts",posts);
            return "blogDetail";
    }
    //完成测试
    @GetMapping("getPostforUser")
    public String getPostForUser(

            Model model) {
//        List<Posts> posts=postsService.list(wrapper);
//        postsPageBean.setItems(posts);

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer uid = (Integer) map.get("id");
        LambdaQueryWrapper<Posts> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Posts::getUserId, uid);
        List<Posts> posts = postsService.list(wrapper);
        PageBean<Posts> postsPageBean = new PageBean<>();
        postsPageBean.setItems(posts);
        model.addAttribute("postsPageBean", postsPageBean);
        return "tpost";
    }


}
