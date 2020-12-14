package com.cbn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cbn.entity.Tag;
import com.cbn.mapper.TagMapper;
import com.cbn.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {


    @Override
    @Transactional
    public int tagCrud(List<Tag> list) {


        List<Tag> list1 = baseMapper.selectList(new QueryWrapper<>());

        List<Long> idList1 = new ArrayList();

        List<Long> idList = new ArrayList();

        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        //数据库id列表
        for (Tag tag1 : list1) {
            idList1.add(tag1.getId());
        }

        for(Tag tag : list){
            //入参id列表
            idList.add(tag.getId());
            //如果数据库中存在
            if (idList1.contains(tag.getId())){
                Tag tag1 = baseMapper.selectById(tag.getId());
                //如果与数据库中不一致则修改
                if (!tag.equals(tag1)){
                   num1 = baseMapper.updateById(tag);
                }
            }
            if (!idList1.contains(tag.getId())){
                num2 = baseMapper.insert(tag);
            }

        }
        //如果数据库里存在入参列表不存在则代表被删除
       for (Tag tag : list1){
           if (!idList.contains(tag.getId())){
               num3 = baseMapper.deleteById(tag.getId());
           }
       }

       int i = num1+num2+num3;
        return i;
    }
}
