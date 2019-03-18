package com.task.pojo;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private Integer pid;
    private Integer m_id;
    private Integer srcUid;
    private Integer descUid;
    private String content;
    private String createTime;
}
