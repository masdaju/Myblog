package com.cg.myblog.mapper;

import com.cg.myblog.pojo.Comments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author MIZUGI
* @description 针对表【comments】的数据库操作Mapper
* @createDate 2024-06-03 18:03:28
* @Entity com.cg.myblog.pojo.Comments
*/
public interface CommentsMapper extends BaseMapper<Comments> {

    List<Comments> getComment(Integer pid);
}




