package com.cbn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cbn.entity.Tag;

import java.util.List;

public interface TagService extends IService<Tag> {

    int tagCrud(List<Tag> tags);
}

