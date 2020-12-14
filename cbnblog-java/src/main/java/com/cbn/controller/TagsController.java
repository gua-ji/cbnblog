package com.cbn.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbn.common.lang.Result;
import com.cbn.entity.Tag;
import com.cbn.service.BlogService;
import com.cbn.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagsController {

    @Autowired
    TagService tagService;
    @Autowired
    BlogService blogService;

    @GetMapping("/tags")
    public Result getTags() {
        List<Tag> tags = tagService.list();
        return Result.succ(tags);
    }

    @GetMapping("/tag/{tag}")
    public Result getTagsById(@PathVariable(name = "tag") String tag,
                              @RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage, 5);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("tag",tag);
        IPage pageData = blogService.page(page, wrapper);
        return Result.succ(pageData);
    }

    @PostMapping("/tags/add")
    public Result addTag(@RequestBody List<Tag> tags){

        int i = tagService.tagCrud(tags);
        return Result.succ(200,"成功操作"+i+"个标签",null);
    }
}
