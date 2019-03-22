package com.task.platform.controller;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.task.common.MD5Utils;
import com.task.pojo.Comment;
import com.task.pojo.DataGridModel;
import com.task.pojo.User;
import com.task.service.ICommentService;
import com.task.service.UserService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.task.platform.controller.BaseController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    private UserService userService;
    //评论
    @RequestMapping("/page/{user_id}")
    public ModelAndView toCommentList(ModelMap modelMap, @PathVariable Integer user_id){
        User user = userService.getObjectById(user_id);
        modelMap.addAttribute("user",user);
        return new ModelAndView("/msg/commentList",modelMap);
    }
    //获取评论分页数据
    @RequestMapping("/findByObjectPageList")
    public void getComments(DataGridModel dataGridModel, HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> params = dataGridModel.getQueryParams();
        try {
            PageBounds pageBounds = new PageBounds(dataGridModel.getPage(),dataGridModel.getRows(), Order.formString(dataGridModel.getSort() + "." + dataGridModel.getOrder()));
            PageList<Comment> pageList = commentService.findByObjectPageList(params, pageBounds);
            if (pageList != null) {
                if(pageList.size() == 0){
                    map.put("code",1);
                    map.put("msg","无数据");
                } else {
                    map.put("code",0);
                    map.put("page", pageList.getPaginator().getTotalPages());
                    map.put("count", pageList.getPaginator().getTotalCount());
                    map.put("data", pageList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1);
            map.put("msg", "获取数据失败!");
        }
        out.print(super.objectToJson(map));
    }
    //点赞或踩或收藏
    @RequestMapping("/deleteComment")
    public void deleteComment(HttpServletRequest request, HttpServletResponse response,@RequestParam Map params){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            commentService.deleteObjectById(params);
            map.put("success", true);
            map.put("msg", "操作成功");
        } catch (Exception e) {
            map.put("success", false);
            map.put("msg", "操作失败");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }
    //添加
    @RequestMapping("/addComment")
    public void addComment(HttpServletRequest request, HttpServletResponse response,Comment comment){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            commentService.addObject(comment);
            map.put("success", true);
            map.put("msg", "操作成功");
        } catch (Exception e) {
            map.put("success", false);
            map.put("msg", "操作失败");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }
}
