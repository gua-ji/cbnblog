package com.cbn.service.impl;

import com.cbn.entity.User;
import com.cbn.mapper.UserMapper;
import com.cbn.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
