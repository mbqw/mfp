package com.task.platform.controller;

import com.task.pojo.DynamicMsg;
import com.task.pojo.User;
import com.task.service.UserService;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;
    //静态资源路径
    @Value(value = "${upload_images_path}")
    private String upload_images_path;
    //跳转到主页
    @RequestMapping("/toDetail/{user_id}/{u_id}")
    public ModelAndView index(ModelMap modelMap, @PathVariable Integer user_id,@PathVariable Integer u_id){
        modelMap.addAttribute("user_id",user_id);
        modelMap.addAttribute("u_id",u_id);
        User user = userService.getObjectById(u_id);
        modelMap.addAttribute("user",user);
        return new ModelAndView("user/detail",modelMap);
    }
    //跳转到更新页面
    @RequestMapping("/toUpdate/{u_id}")
    public ModelAndView toUpdate(ModelMap modelMap, @PathVariable Integer u_id){
        User user = userService.getObjectById(u_id);
        modelMap.addAttribute("user",user);
        return new ModelAndView("user/update",modelMap);
    }
    //更新
    @RequestMapping("/update")
    public void islike(HttpServletRequest request, HttpServletResponse response, User user){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            userService.updateObject(user);
            map.put("success", true);
            map.put("info", "操作成功");
        } catch (Exception e) {
            map.put("success", false);
            map.put("info", "操作失败");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }

}
