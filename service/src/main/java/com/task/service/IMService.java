package com.task.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.task.pojo.IMData;

import java.util.Map;

public interface IMService extends BaseService<IMData>{
    PageList<Map> getGroups(Object id);
    PageList<Map> findFriendsPageList(Map<String, Object> params, PageBounds pageBounds);
    PageList<Map> findGroupsPageList(Map<String, Object> params, PageBounds pageBounds);
    void deleteGroup(Map params);
    void moveFriend(Map params);
    void deleteFriend(Map params);
    void batchDelete(Map params);
    void addGroup(Map params);
    boolean isFriend(Integer user_id,Integer u_id);
}
