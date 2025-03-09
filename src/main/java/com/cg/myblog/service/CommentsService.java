package com.cg.myblog.service;

import com.cg.myblog.pojo.Comments;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cg.myblog.utils.PageBean;

import java.util.List;

/**
* @author MIZUGI
* @description 针对表【comments】的数据库操作Service
* @createDate 2024-06-03 18:03:28
*/
public interface CommentsService extends IService<Comments> {



    void deleteComment(Integer id,Integer uid, Integer pid);

    void updateComment(Integer id,Integer pid,Integer uid, String content);

    List<Comments> getComment(Integer pid);

    void addComment(Integer postId, String content);
}
