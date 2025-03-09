package com.cg.myblog.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cg.myblog.pojo.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cg.myblog.utils.PageBean;

/**
* @author MIZUGI
* @description 针对表【users】的数据库操作Service
* @createDate 2024-06-03 18:03:28
*/
public interface UsersService extends IService<Users> {

     PageBean<Users> list(Integer pageNum, Integer pageSize);

    void register(Users users);
}
