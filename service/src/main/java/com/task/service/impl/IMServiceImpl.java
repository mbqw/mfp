package com.task.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.task.dao.UserGroupMapper;
import com.task.pojo.IMData;
import com.task.pojo.UserGroup;
import com.task.service.IMService;
import com.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
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
    public List<UserGroup> getGroupById(Object id){
        return mapper.getGroupById(Integer.parseInt((String) id));
    }
    public List<UserGroup> getFriendsById(Object id){
        return mapper.getFriendsById(Integer.parseInt((String) id));
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
    public boolean isFriend(Integer user_id,Integer u_id){
        return mapper.isFriend(user_id,u_id);
    }
    @Override
    public List<Integer> getGroupMembersById(Object id) {
        return mapper.getGroupMembersById(id);
    }
    @Override
    public Integer disAgreeMsgbox(Object id) {
        return mapper.disAgreeMsgbox(id);
    }
    public void addMsgbox(Map map){
        mapper.addMsgbox(map);
    }
    public PageList<Map> findMsgboxPageList(Map<String, Object> params, PageBounds pageBounds){
        return mapper.findMsgboxPageList(params,pageBounds);
    }
    public void addFriend(Map params){
        mapper.updateMsgbox(params);
        Integer agree = Integer.parseInt(params.get("agree")+"");
        if (agree == 1){
            Map map = new HashMap();
            map.put("u_id",params.get("descUid"));
            map.put("id",params.get("srcGid"));
            mapper.addMember(map);
            map.put("u_id",params.get("srcUid"));
            map.put("id",params.get("descGid"));
            mapper.addMember(map);
        }
    }
    public List addMember(Map params){
        Object id = params.get("id");
        String[] ids = ((String) params.get("ids")).split(",");
        List list = new ArrayList();
        for (String s : ids) {
            Map map = new HashMap();
            map.put("id",id);
            map.put("u_id",s);
            Integer i = mapper.addMember(map);
            if (i != null && i != 0){
                list.add(s);
            }
        }
        return list;
    }
    public List<Map> getMembers(Integer id){
        return mapper.getMembers(id);
    }
    public void addChat(Map map){
        mapper.addChat(map);
    }
    public void updateChatStatus(Map map){
        mapper.updateChatStatus(map);
    }
    public List<Map> getOfflineChat(Integer id){
        return mapper.getOfflineChat(id);
    }

    @Override
    public PageList<Map> findChatlogPageList(Map<String, Object> params, PageBounds pageBounds) {
        return mapper.findChatlogPageList(params,pageBounds);
    }
    public Integer getChatlogCount(Map map){
        return mapper.getChatlogCount(map);
    }
}
