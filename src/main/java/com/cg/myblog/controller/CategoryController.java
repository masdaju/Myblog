package com.cg.myblog.controller;


import com.cg.myblog.pojo.Categories;
import com.cg.myblog.service.CategoriesService;
import com.cg.myblog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//全部只有管理员可以操作
@Controller
public class CategoryController {
    @Autowired
    CategoriesService categoriesService;
    @PostMapping("addCategory")//添加类别
    @ResponseBody
    public Result addCategory(@RequestBody Categories categories){
        categoriesService.addCategory(categories);
        return Result.success();
    }
    @PostMapping("deleteCategory")//删除类别
    @ResponseBody
    public Result deleteCategory(@RequestParam("id") Integer id){
        categoriesService.deleteCategory(id);
        return Result.success();
    }
    @GetMapping("getCategory")//获取分类
    @ResponseBody
    public Result<List<Categories>> getCategory() {
        List<Categories> categories = categoriesService.list();
        return Result.success(categories);
    }
}
