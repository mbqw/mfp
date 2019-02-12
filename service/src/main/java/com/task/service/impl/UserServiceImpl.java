package com.task.service.impl;

import com.task.dao.UserMapper;
import com.task.pojo.User;
import com.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService  {
    @Autowired
    private UserMapper mapper;


}
