package com.cg.myblog.service;

import com.cg.myblog.pojo.Likes;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cg.myblog.utils.Result;

import java.util.List;

/**
* @author MIZUGI
* @description 针对表【likes】的数据库操作Service
* @createDate 2024-06-03 18:03:28
*/
public interface LikesService extends IService<Likes> {

    void add(Likes likes);

    void delete(Likes likes);

    List<Likes> getlist(Integer uid);

}
