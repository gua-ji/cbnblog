package com.cbn.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbn.entity.Blog;
import com.cbn.mapper.BlogMapper;
import com.cbn.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Override
    @Transactional
    public Blog selectById(Long id) {
        Blog blog = baseMapper.selectById(id);
        int views = blog.getViews();
        blog.setViews(views + 1);
        baseMapper.updateById(blog);
        return blog;
    }

    @Override
    public IPage<Blog> deletedBlogs(Page<Blog> page) {
        IPage<Blog> blogs = blogMapper.deletedBlogs(page);
        return blogs;
    }

    @Override
    public int recoveryById(Long id) {
        return blogMapper.recoveryById(id);
    }
}
