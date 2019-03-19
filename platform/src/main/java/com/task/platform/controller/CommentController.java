package com.task.platform.controller;

import com.task.common.MD5Utils;
import com.task.pojo.Comment;
import com.task.pojo.User;
import com.task.service.ICommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.task.platform.controller.BaseController;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 动态消息的评论表 前端控制器
 * </p>
 *
 * @author wubo
 * @since 2019-03-19
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController {
    @Autowired
    private ICommentService commentService;

    @RequestMapping("/all")
    @ResponseBody
    public void register(HttpServletResponse response, User user){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Comment> objectList = commentService.getObjectList(null);
            map.put("objectList", objectList);
        } catch (Exception e) {
            map.put("success", false);
            map.put("info", "注册失败!");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }
}
