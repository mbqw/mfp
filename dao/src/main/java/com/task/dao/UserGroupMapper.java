package com.task.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.task.pojo.User;
import com.task.pojo.UserGroup;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserGroupMapper extends BaseMapper<UserGroup> {
    PageList<Map> getGroups(Object id);
    List<UserGroup> getFriendsById(Integer id);
    List<UserGroup> getGroupById(Integer id);
    PageList<Map> findFriendsPageList(Map<String, Object> params, PageBounds pageBounds);
    PageList<Map> findGroupsPageList(Map<String, Object> params, PageBounds pageBounds);
    void deleteGroup(Integer user_id, Integer group_id);
}
