package com.task.platform.controller;

import com.task.service.DynamicMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/msg")
public class MsgController extends BaseController{
    @Autowired
    private DynamicMsgService dynamicMsgService;
    //跳转到主页
    @RequestMapping("/toAdd/{id}")
    public ModelAndView toAdd(ModelMap modelMap, @PathVariable Integer id){
        modelMap.addAttribute("id",id);
        //User objectById = userService.getObjectById("");
        return new ModelAndView("/add",modelMap);
    }

}
