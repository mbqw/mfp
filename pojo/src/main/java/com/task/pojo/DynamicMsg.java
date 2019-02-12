package com.task.pojo;

import lombok.Data;

@Data
public class DynamicMsg {
    private Integer id;
    private String content;
    private String[] img;
    private Integer like;
    private Integer unlike;
    private Integer u_id;
    private String createTime;
    private User user;
}
