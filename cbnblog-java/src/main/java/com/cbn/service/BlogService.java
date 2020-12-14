package com.cbn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbn.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;


public interface BlogService extends IService<Blog> {

    Blog selectById(Long id);
    IPage<Blog> deletedBlogs(Page<Blog> page);
    int recoveryById(Long id);
}
