package com.cg.myblog.controller;

import com.cg.myblog.pojo.Comments;
import com.cg.myblog.service.CommentsService;
import com.cg.myblog.utils.Result;
import com.cg.myblog.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class CommentController {
    @Autowired
    CommentsService commentsService;
    @PostMapping("addcommnent")//添加评论
    public Result addcomment( @RequestParam("postId") Integer postId,
                              @RequestParam("content") String content){
        commentsService.addComment(postId,content);
        return Result.success();
    }

    @PostMapping("deleteComment")//删除评论
    public Result deleteComment(
            @RequestParam("id") Integer id //评论id
            ,@RequestParam("uid") Integer uid //用户id
            , @RequestParam("pid") Integer pid){ //文章id
            Map<Object,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        if (Objects.equals(uid, userid)){
        commentsService.deleteComment(id,uid,pid);
        return Result.success();
        }
        return Result.error("这不是你的评论");
    }
    @PostMapping("updateComment")//更新评论
    public Result updateComment(Integer id,Integer pid,Integer uid ,String content){
        commentsService.updateComment(id,pid,uid,content);
        return Result.success();
    }

    @GetMapping("CommentForPost")//分页获取当前文章的评论
    public Result<List<Comments>> getComment(@RequestParam("pid")Integer pid){
        List<Comments> commentsList =commentsService.getComment(pid);
        return Result.success(commentsList);
    }
}
