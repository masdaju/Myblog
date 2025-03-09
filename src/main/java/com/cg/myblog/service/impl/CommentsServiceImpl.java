package com.cg.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cg.myblog.pojo.Comments;
import com.cg.myblog.pojo.Users;
import com.cg.myblog.service.CommentsService;
import com.cg.myblog.mapper.CommentsMapper;
import com.cg.myblog.utils.PageBean;
import com.cg.myblog.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author MIZUGI
* @description 针对表【comments】的数据库操作Service实现
* @createDate 2024-06-03 18:03:28
*/
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments>
    implements CommentsService{
@Autowired
CommentsMapper commentsMapper;

@Override
public void addComment(Integer postId, String content) {
            Map<String,Object> map = ThreadLocalUtil.get();
            Comments comments =new Comments();
            comments.setPostId(postId);
            comments.setContent(content);
        comments.setUserId((Integer) map.get("id"));
        commentsMapper.insert(comments);

}
    @Override
    public void deleteComment(Integer id,Integer uid, Integer pid) {
        LambdaQueryWrapper<Comments> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(Comments::getId,id);
        wrapper.eq(Comments::getPostId,pid);
        wrapper.eq(Comments::getUserId,uid);
        commentsMapper.delete(wrapper);
    }

    @Override
    public void updateComment( Integer id,Integer pid,Integer uid, String content) {
        LambdaUpdateWrapper<Comments> wrapper =new LambdaUpdateWrapper<>();
        wrapper.eq(Comments::getPostId,pid)
                .eq(Comments::getId,id)
                .eq(Comments::getUserId,uid)
                .set(Comments::getContent,content);
        commentsMapper.update(wrapper);
    }

    @Override
    public List<Comments> getComment(Integer pid) {
//        LambdaQueryWrapper<Comments> wrapper =new LambdaQueryWrapper<>();
//        wrapper.eq(Comments::getPostId,pid);
        return commentsMapper.getComment(pid);
    }



}




