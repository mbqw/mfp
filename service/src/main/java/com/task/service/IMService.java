package com.task.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.task.pojo.IMData;
import com.task.pojo.UserGroup;

import java.util.List;
import java.util.Map;

public interface IMService extends BaseService<IMData>{
    PageList<Map> getGroups(Object id);
    List<UserGroup> getGroupById(Object id);
    List<UserGroup> getFriendsById(Object id);
    PageList<Map> findFriendsPageList(Map<String, Object> params, PageBounds pageBounds);
    PageList<Map> findGroupsPageList(Map<String, Object> params, PageBounds pageBounds);
    void deleteGroup(Map params);
    void moveFriend(Map params);
    void deleteFriend(Map params);
    void batchDelete(Map params);
    void addGroup(Map params);
    boolean isFriend(Integer user_id,Integer u_id);
    List<Integer> getGroupMembersById(Object id);
    Integer disAgreeMsgbox(Object id);
    void addMsgbox(Map map);
    PageList<Map> findMsgboxPageList(Map<String, Object> params, PageBounds pageBounds);
    void addFriend(Map params);
    List<Map> getMembers(Integer id);
    void addChat(Map map);
    List addMember(Map map);
    void updateChatStatus(Map map);
    List<Map> getOfflineChat(Integer id);
    PageList<Map> findChatlogPageList(Map<String, Object> params, PageBounds pageBounds);
    Integer getChatlogCount(Map map);

}
