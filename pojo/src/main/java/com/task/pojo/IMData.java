package com.task.pojo;

import lombok.Data;

import java.util.List;

@Data
public class IMData {
    private User mine;
    private List<UserGroup> friend;
    private List<UserGroup> group;
}
