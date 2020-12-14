package com.cbn.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbn.VO.BlogForm;
import com.cbn.common.lang.Result;
import com.cbn.entity.Blog;
import com.cbn.service.BlogService;
import com.cbn.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class BlogController {

    @Autowired
    BlogService blogService;



    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {

        Page page = new Page(currentPage, 5);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));

        return Result.succ(pageData);
    }

    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.selectById(id);
        Assert.notNull(blog, "该博客已被删除");

        return Result.succ(blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody BlogForm blog) {

        Blog temp = null;
        if(blog.getId() != null) {
            //编辑状态
            temp = blogService.getById(blog.getId());
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(), "没有权限编辑");

        } else {
            //新增状态
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
        }

        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "deleted");
        blogService.saveOrUpdate(temp);

        return Result.succ(null);
    }

    @DeleteMapping("/blog/{id}")
    public Result deletdById(@PathVariable(name = "id")Long id){
        boolean b = blogService.removeById(id);
        if(b){
        return Result.succ(null);
        }
        return Result.fail("删除失败");
    }

    @GetMapping("/admin/gcblogs")
    public Result gcBlogs(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page page = new Page(currentPage, 5);
        IPage pageData = blogService.deletedBlogs(page);
        return Result.succ(pageData);
    }

    @PutMapping("/blog/recovery/{id}")
    public Result recoveryById(@PathVariable(name = "id")Long id){
        blogService.recoveryById(id);
        return Result.succ(null);
    }

}
