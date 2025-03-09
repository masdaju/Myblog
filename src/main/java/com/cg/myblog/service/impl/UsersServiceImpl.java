package com.cg.myblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cg.myblog.mapper.UsersMapper;
import com.cg.myblog.pojo.Users;
import com.cg.myblog.service.UsersService;
import com.cg.myblog.utils.PageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author MIZUGI
* @description 针对表【users】的数据库操作Service实现
* @createDate 2024-06-03 18:03:28
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{
@Autowired
UsersMapper usersMapper;
    @Override
    public PageBean<Users> list(Integer pageNum, Integer pageSize) {
//        Page<Users> page = new Page<>(pageNum, pageSize);
        PageBean<Users> pb = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);

       List<Users> users = usersMapper.selectList(null);
        Page<Users> p = (Page<Users>) users;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void register(Users users) {
        usersMapper.insert(users);
    }
}




