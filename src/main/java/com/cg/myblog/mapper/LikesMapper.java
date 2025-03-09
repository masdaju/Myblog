package com.cg.myblog.mapper;

import com.cg.myblog.pojo.Likes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author MIZUGI
* @description 针对表【likes】的数据库操作Mapper
* @createDate 2024-06-03 18:03:28
* @Entity com.cg.myblog.pojo.Likes
*/
public interface LikesMapper extends BaseMapper<Likes> {

    void add(@Param("likes") Likes likes);
//    @Select("SELECT l.*, p.* FROM likes l JOIN posts p ON l.post_id = p.id WHERE l.user_id = #{uid}")
    List<Likes> getlist(Integer uid);
}




