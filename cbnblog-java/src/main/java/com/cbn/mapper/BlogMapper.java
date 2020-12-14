package com.cbn.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbn.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface BlogMapper extends BaseMapper<Blog> {
    IPage<Blog> deletedBlogs(Page<?> page);
    int recoveryById(Long id);

}
