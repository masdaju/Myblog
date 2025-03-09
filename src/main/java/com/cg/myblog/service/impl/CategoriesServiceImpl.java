package com.cg.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cg.myblog.pojo.Categories;
import com.cg.myblog.service.CategoriesService;
import com.cg.myblog.mapper.CategoriesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author MIZUGI
* @description 针对表【categories】的数据库操作Service实现
* @createDate 2024-06-03 18:03:28
*/
@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, Categories>
    implements CategoriesService{
@Autowired
CategoriesMapper categoriesMapper;
    @Override
    public void addCategory(Categories categories) {
        categoriesMapper.insert(categories);
    }

    @Override
    public void deleteCategory(Integer id) {
        LambdaQueryWrapper<Categories> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Categories::getId,id);
        categoriesMapper.delete(wrapper);
    }
}




