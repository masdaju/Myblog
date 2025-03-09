package com.cg.myblog.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//分页返回结果对象
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    private List<T> items;
    private long total;
    private int pageNum;
    private int pageSize;
    private int pages;

    // Getters and setters
}
