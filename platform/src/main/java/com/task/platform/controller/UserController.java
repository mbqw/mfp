package com.task.platform.controller;

import com.task.pojo.DynamicMsg;
import com.task.pojo.User;
import com.task.service.UserService;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    @RequestMapping("/toDetail/{id}")
    public ModelAndView index(ModelMap modelMap, @PathVariable Integer id){
        modelMap.addAttribute("id",id);
        User user = userService.getObjectById(id);
        modelMap.addAttribute("user",user);
        return new ModelAndView("user/detail",modelMap);
    }
    @RequestMapping("/detail/{id}")
    public void add(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User user = userService.getObjectById(id);
            BeanMap beanMap = new BeanMap(user);
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("info", "发布动态失败");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }

}
