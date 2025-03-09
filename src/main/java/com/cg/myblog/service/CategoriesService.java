package com.cg.myblog.service;

import com.cg.myblog.pojo.Categories;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author MIZUGI
* @description 针对表【categories】的数据库操作Service
* @createDate 2024-06-03 18:03:28
*/
public interface CategoriesService extends IService<Categories> {

    void addCategory(Categories categories);

    void deleteCategory(Integer id);
}
