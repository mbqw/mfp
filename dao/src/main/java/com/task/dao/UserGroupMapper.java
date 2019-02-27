package com.task.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.task.pojo.UserGroup;
import org.apache.ibatis.annotations.Param;
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
    void deleteGroup(Map params);
    void moveFriend(Map params);
    void deleteFriend(Map params);
    void batchDelete(Map params);
    void addGroup(Map params);
    void addMember(Map params);
    boolean isFriend(@Param("user_id") Integer user_id,@Param("u_id") Integer u_id);
}
