package com.task.platform.controller;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.task.pojo.DataGridModel;
import com.task.pojo.DynamicMsg;
import com.task.pojo.IMData;
import com.task.pojo.User;
import com.task.service.IMService;
import com.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/im")
public class IMController extends BaseController {

    @Autowired
    private IMService imService;
    @Autowired
    private UserService userService;
    //跳转到Im
    @RequestMapping("/toList/{id}")
    public ModelAndView detail(ModelMap modelMap, @PathVariable Integer id){
        modelMap.addAttribute("id",id);
        return new ModelAndView("/user/im",modelMap);
    }
    //初始化数据
    @RequestMapping("/init")
    public void init(HttpServletRequest request, HttpServletResponse response, Integer id){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            IMData imData = imService.getObjectById(id);
            map.put("code", 0);
            map.put("data",imData);
        } catch (Exception e) {
            map.put("code", 1);
            map.put("msg", "获取信息失败");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }
    //获取好友分页数据
    @RequestMapping("/friendsList")
    public void friendsList(DataGridModel dataGridModel, HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> params = dataGridModel.getQueryParams();
        try {
            PageBounds pageBounds = new PageBounds(dataGridModel.getPage(),dataGridModel.getRows(), Order.formString(dataGridModel.getSort() + "." + dataGridModel.getOrder()));
            PageList<Map> pageList = imService.findFriendsPageList(params, pageBounds);
            if (pageList != null) {
                map.put("count", pageList.getPaginator().getTotalCount());
                map.put("data", pageList);
                map.put("groupList",imService.getGroups(params.get("id")));
                if(pageList.size() == 0){
                    map.put("code",1);
                    map.put("msg","无数据");
                } else {
                    map.put("code",0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1);
            map.put("msg", "获取数据失败!");
        }
        out.print(super.objectToJson(map));
    }
    //获取群组数据
    @RequestMapping("/groupsList")
    public void groupList(DataGridModel dataGridModel, HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> params = dataGridModel.getQueryParams();
        try {
            PageBounds pageBounds = new PageBounds(dataGridModel.getPage(),dataGridModel.getRows(), Order.formString(dataGridModel.getSort() + "." + dataGridModel.getOrder()));
            PageList<Map> pageList = imService.findGroupsPageList(params, pageBounds);
            if (pageList != null) {
                map.put("count", pageList.getPaginator().getTotalCount());
                map.put("data", pageList);
                if(pageList.size() == 0){
                    map.put("code",1);
                    map.put("msg","无数据");
                } else {
                    map.put("code",0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1);
            map.put("msg", "获取数据失败!");
        }
        out.print(super.objectToJson(map));
    }
    //删除群组
    @RequestMapping("/deleteGroup")
    public void deleteGroup(HttpServletRequest request, HttpServletResponse response, @RequestParam Map params){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            imService.deleteGroup(params);
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("msg", "操作失败");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }
    //删除好友
    @RequestMapping("/deleteFriend")
    public void deleteFriend(HttpServletRequest request, HttpServletResponse response, @RequestParam Map params){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            imService.deleteFriend(params);
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("msg", "操作失败");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }
    //批量删除好友
    @RequestMapping("/batchDelete")
    public void batchDelete(HttpServletRequest request, HttpServletResponse response, @RequestParam Map params){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            imService.batchDelete(params);
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("msg", "操作失败");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }
    //移动好友至其它分组
    @RequestMapping("/moveFriend")
    public void moveFriend(HttpServletRequest request, HttpServletResponse response,@RequestParam Map params){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            imService.moveFriend(params);
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("msg", "操作失败");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }
    //添加分组
    @RequestMapping("/addGroup")
    public void addGroup(HttpServletRequest request, HttpServletResponse response,@RequestParam Map params){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            imService.addGroup(params);
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("msg", "操作失败");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }

}
