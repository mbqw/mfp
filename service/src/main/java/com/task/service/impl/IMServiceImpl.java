package com.task.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.task.dao.UserGroupMapper;
import com.task.pojo.IMData;
import com.task.pojo.User;
import com.task.pojo.UserGroup;
import com.task.service.IMService;
import com.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("imService")
@Transactional
public class IMServiceImpl extends BaseServiceImpl<IMData> implements IMService {
    @Autowired
    private UserGroupMapper mapper;

    @Autowired
    private UserService userService;

    public PageList<Map> getGroups(Object id){
        return mapper.getGroups(id);
    }

    @Override
    public IMData getObjectById(Object id) {
        IMData imData = new IMData();
        //UserGroup userGroup = new UserGroup();
        imData.setMine(userService.getObjectById(id));
        imData.setFriend(mapper.getFriendsById((Integer) id));
        imData.setGroup(mapper.getGroupById((Integer) id));
        return imData;
    }

    @Override
    public PageList<Map> findFriendsPageList(Map<String, Object> params, PageBounds pageBounds) {
        return mapper.findFriendsPageList(params,pageBounds);
    }

    @Override
    public PageList<Map> findGroupsPageList(Map<String, Object> params, PageBounds pageBounds) {
        return mapper.findGroupsPageList(params,pageBounds);
    }

    @Override
    public void deleteGroup(Integer user_id, Integer group_id) {
        mapper.deleteGroup(user_id,group_id);
    }
}
