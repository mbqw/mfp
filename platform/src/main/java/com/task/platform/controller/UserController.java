package com.task.platform.controller;

import com.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;
    //跳转到主页
    @RequestMapping("/index/{id}")
    public ModelAndView index(ModelMap modelMap, @PathVariable Integer id){
        modelMap.addAttribute("id",id);
        //User objectById = userService.getObjectById("");
        return new ModelAndView("platform",modelMap);
    }
}
