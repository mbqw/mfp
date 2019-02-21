package com.task.service.impl;

import com.task.dao.UserGroupMapper;
import com.task.pojo.IMData;
import com.task.pojo.UserGroup;
import com.task.service.IMService;
import com.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("imService")
@Transactional
public class IMServiceImpl extends BaseServiceImpl<IMData> implements IMService {
    @Autowired
    private UserGroupMapper mapper;

    @Autowired
    private UserService userService;

    @Override
    public IMData getObjectById(Object id) {
        IMData imData = new IMData();
        //UserGroup userGroup = new UserGroup();
        imData.setMine(userService.getObjectById(id));
        imData.setFriend(mapper.getFriendsById((Integer) id));
        imData.setGroup(mapper.getGroupById((Integer) id));
        return imData;
    }
}
