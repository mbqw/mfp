package com.task.pojo;

import lombok.Data;

import java.util.List;

@Data
public class UserGroup {
    private Integer id;
    private String groupname;
    private String avatar;
    private Integer u_id;
    private Integer type;
    private List<User> list;

}
