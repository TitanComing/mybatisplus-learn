package com.peng.mybatisplus.crud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peng.mybatisplus.crud.entity.User;
import com.peng.mybatisplus.crud.mapper.UserMapper;
import com.peng.mybatisplus.crud.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by peng on 2020/2/14.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
