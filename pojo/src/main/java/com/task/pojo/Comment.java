package com.task.pojo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>
 * 动态消息的评论表
 * </p>
 *
 * @author wubo
 * @since 2019-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 父id,用来判断属于'某楼层'的评论
     */
    private Integer pid;

    /**
     * u_dyn_msg表id
     */
    private Integer mId;

    private Integer srcUid;

    private Integer descUid;

    private String content;

    private String createTime;


}
