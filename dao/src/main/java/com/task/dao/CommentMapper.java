package com.task.dao;

import com.task.pojo.Comment;
import com.task.dao.BaseMapper;
import org.springframework.stereotype.Repository;
/**
 * <p>
 * 动态消息的评论表 Mapper 接口
 * </p>
 *
 * @author wubo
 * @since 2019-03-19
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

}
