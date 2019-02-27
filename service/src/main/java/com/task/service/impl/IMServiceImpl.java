package com.task.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.task.dao.UserGroupMapper;
import com.task.pojo.IMData;
import com.task.service.IMService;
import com.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void deleteGroup(Map params) {
        mapper.deleteGroup(params);
    }
    @Override
    public void moveFriend(Map params) {
        mapper.moveFriend(params);
    }
    @Override
    public void deleteFriend(Map params) {
        mapper.deleteFriend(params);
    }
    @Override
    public void batchDelete(Map params) {
        mapper.batchDelete(params);
    }
    @Override
    public void addGroup(Map params) {
        Integer type = Integer.valueOf(params.get("type").toString());
        mapper.addGroup(params);
        if (type != null && type == 1) {
            mapper.addMember(params);
        }
    }
}
