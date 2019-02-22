package com.task.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.task.pojo.IMData;
import com.task.pojo.User;
import com.task.pojo.UserGroup;

import java.util.Map;

public interface IMService extends BaseService<IMData>{
    PageList<Map> getGroups(Object id);
    PageList<Map> findFriendsPageList(Map<String, Object> params, PageBounds pageBounds);
    PageList<Map> findGroupsPageList(Map<String, Object> params, PageBounds pageBounds);
    void deleteGroup(Integer user_id,Integer group_id);
}
