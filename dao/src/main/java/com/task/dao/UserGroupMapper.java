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
    //获取所有好友分组
    PageList<Map> getGroups(Object id);
    //获取所有群
    List<UserGroup> getGroupById(Integer id);
    //获取每个好友的分组信息
    List<UserGroup> getFriendsById(Integer id);
    //分页获取每个好友的分组信息
    PageList<Map> findFriendsPageList(Map<String, Object> params, PageBounds pageBounds);
    //分页获取分组和群
    PageList<Map> findGroupsPageList(Map<String, Object> params, PageBounds pageBounds);
    //删除群组
    void deleteGroup(Map params);
    //移动好友分组
    void moveFriend(Map params);
    //删除好友
    void deleteFriend(Map params);
    //批量删除好友
    void batchDelete(Map params);
    //添加群组
    void addGroup(Map params);
    //群组下添加成员
    Integer addMember(Map params);
    //判断是否是好友
    boolean isFriend(@Param("user_id") Integer user_id,@Param("u_id") Integer u_id);
    //获取群组下的成员ID
    List<Integer> getGroupMembersById(@Param("id")Object id);
    //获取未操作的消息盒子的消息数量
    Integer disAgreeMsgbox(@Param("id")Object id);
    //添加消息盒子
    void addMsgbox(Map map);
    //分页获取消息盒子
    PageList<Map> findMsgboxPageList(Map<String, Object> params, PageBounds pageBounds);
    //更新消息盒子
    void updateMsgbox(Map params);
    //获取群组下成员详细信息
    List<Map> getMembers(@Param("id") Integer id);
    //添加聊天记录
    void addChat(Map map);
    //修改聊天记录的状态(已读未读)
    void updateChatStatus(Map map);
    //获取未读信息
    List<Map> getOfflineChat(@Param("id") Integer id);
    //分页获取聊天记录
    PageList<Map> findChatlogPageList(Map<String, Object> params, PageBounds pageBounds);
    //获取聊天记录总条数
    Integer getChatlogCount(Map map);
}
