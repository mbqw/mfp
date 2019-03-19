package com.task.service.impl;

import com.task.pojo.Comment;
import com.task.dao.CommentMapper;
import com.task.service.ICommentService;
import com.task.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * <p>
 * 动态消息的评论表 服务实现类
 * </p>
 *
 * @author wubo
 * @since 2019-03-19
 */
@Transactional
@Service("commentService")
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;
}
