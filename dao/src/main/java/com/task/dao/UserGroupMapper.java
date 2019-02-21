package com.task.dao;

import com.task.pojo.UserGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupMapper extends BaseMapper<UserGroup> {
    List<UserGroup> getFriendsById(Integer id);
    List<UserGroup> getGroupById(Integer id);
}
