package com.cbn.controller;

import cn.hutool.core.bean.BeanUtil;
import com.cbn.common.lang.Result;
import com.cbn.VO.CommentForm;
import com.cbn.entity.Comment;
import com.cbn.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/comment/add")
    public Result addComment(@Validated @RequestBody CommentForm formLabelAlign) {
        Comment comment = new Comment();
        BeanUtil.copyProperties(formLabelAlign, comment);
        commentService.save(comment);
        return Result.succ(null);
    }

    @GetMapping("/comments")
    public Result getAllComments() {
        List<Comment> list = commentService.list();
        return Result.succ(list);
    }

    @DeleteMapping("/comments/deleted/{id}")
    public Result getCommentById(@PathVariable(name = "id") Long id) {
        boolean b = commentService.removeById(id);
        if (b) { return Result.succ(null); }
        return Result.fail(null);
    }
}

