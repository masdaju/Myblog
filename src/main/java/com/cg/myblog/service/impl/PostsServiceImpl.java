package com.cg.myblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cg.myblog.pojo.Posts;
import com.cg.myblog.service.PostsService;
import com.cg.myblog.mapper.PostsMapper;
import org.springframework.stereotype.Service;

/**
* @author MIZUGI
* @description 针对表【posts】的数据库操作Service实现
* @createDate 2024-06-03 18:03:28
*/
@Service
public class PostsServiceImpl extends ServiceImpl<PostsMapper, Posts>
    implements PostsService{

}




