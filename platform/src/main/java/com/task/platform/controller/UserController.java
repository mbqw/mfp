package com.task.platform.controller;

import com.task.pojo.User;
import com.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    //跳转到聊天页面
    @RequestMapping("/chat/{user_id}")
    public ModelAndView chat(ModelMap modelMap, @PathVariable Integer user_id){
        User user = userService.getObjectById(user_id);
        modelMap.addAttribute("user",user);
        return new ModelAndView("user/chat2",modelMap);
    }
    //更新
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response, User user){
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
    //跳转到更换头像页面
    @RequestMapping("/toAvatar/{user_id}")
    public ModelAndView toAvatar(ModelMap modelMap, @PathVariable Integer user_id){
        modelMap.addAttribute("user_id",user_id);
        modelMap.addAttribute("avatar",userService.getObjectById(user_id).getAvatar());

        return new ModelAndView("user/avatar",modelMap);
    }
    //更换头像
    @RequestMapping("/avatar")
    public void avatar(HttpServletRequest request, HttpServletResponse response,@RequestParam("imagefile") MultipartFile file,Integer id){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (!file.isEmpty()){
                File dir = new File(upload_images_path+"head\\");
                if (!dir.exists()){
                    dir.mkdir();
                }
                String fileName=id+".png";
                file.transferTo(new File(dir,fileName));
                User user = new User();
                user.setId(id);
                user.setAvatar("\\head\\"+fileName);
                userService.updateObject(user);
                map.put("success", true);
                map.put("info","\\head\\"+fileName);
            }else{
                map.put("success", false);
                map.put("info", "文件为空");
            }
        } catch (Exception e) {
            map.put("success", false);
            map.put("info", "操作失败");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }

}
