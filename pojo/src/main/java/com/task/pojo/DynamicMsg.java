package com.task.pojo;

import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class DynamicMsg implements Serializable {
    private Integer id;
    private String content;
    private String[] img;
    private Integer ylike;
    private Integer unlike;
    private Integer star;
    private Integer u_id;
    private String createTime;
    private User user;

}
