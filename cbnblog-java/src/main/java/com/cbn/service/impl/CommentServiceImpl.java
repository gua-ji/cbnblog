package com.cbn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cbn.entity.Comment;
import com.cbn.mapper.CommentMapper;
import com.cbn.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
}
