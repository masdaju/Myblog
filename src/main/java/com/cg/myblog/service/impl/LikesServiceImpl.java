package com.cg.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cg.myblog.pojo.Likes;
import com.cg.myblog.service.LikesService;
import com.cg.myblog.mapper.LikesMapper;
import com.cg.myblog.utils.Result;
import com.cg.myblog.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author MIZUGI
* @description 针对表【likes】的数据库操作Service实现
* @createDate 2024-06-03 18:03:28
*/
@Service
public class LikesServiceImpl extends ServiceImpl<LikesMapper, Likes>
    implements LikesService{
    @Autowired
    LikesMapper likesMapper;
    @Override
    public void add(Likes likes) {
        likesMapper.add(likes);
    }

    @Override
    public void delete(Likes likes) {
        LambdaQueryWrapper<Likes> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Likes::getUserId,likes.getUserId());
        wrapper.eq(Likes::getPostId,likes.getPostId());
        likesMapper.delete(wrapper);
    }

    @Override
    public List<Likes> getlist(Integer uid) {
       return likesMapper.getlist(uid);
    }


}




