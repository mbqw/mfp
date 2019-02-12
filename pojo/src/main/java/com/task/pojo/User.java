package com.task.pojo;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Integer id;
    private String username;
    private String account;
    private String password;
    private Integer age;
    private Integer sex;
    private String email;
    private String phone;
    private String address;
    private String headshow;
    private String sign;
    private List<DynamicMsg> dynamicMsgs;

}
