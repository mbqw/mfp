package com.task.platform.controller;

import com.task.common.MD5Utils;
import com.task.pojo.User;
import com.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 跳转到登陆页面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    /**
     * 注册
     * @param response
     * @param user
     */
    @RequestMapping("/register")
    public void register(HttpServletResponse response, User user){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            user.setPassword(MD5Utils.getMd5Str(user.getPassword()));
            userService.addObject(user);
            map.put("success", true);
            map.put("id", user.getId());
        } catch (Exception e) {
            map.put("success", false);
            map.put("info", "注册失败!");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }

    /**
     * 验证登陆
     * @param response
     * @param user
     */
    @RequestMapping("/toLogin")
    public void toLogin(HttpServletResponse response, User user){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            user.setPassword(MD5Utils.getMd5Str(user.getPassword()));
            List<User> userList = userService.getObjectList(user);
            if (userList!=null &&userList.size()>0){
                map.put("success", true);
                map.put("id", userList.get(0).getId());
            } else{
                map.put("success", false);
                map.put("info", "账号或密码不正确");
            }
        } catch (Exception e) {
            map.put("success", false);
            map.put("info", "登陆失败!");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }
    /**
     * 重置密码前验证
     * @param response
     * @param user
     */
    @RequestMapping("/checkPE")
    public void checkPE(HttpServletResponse response, User user){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<User> userList = userService.getObjectList(user);
            if (userList!=null &&userList.size()>0){
                map.put("success", true);
                map.put("id", userList.get(0).getId());
            } else{
                map.put("success", false);
                map.put("info", "账号不存在");
            }
        } catch (Exception e) {
            map.put("success", false);
            map.put("info", "验证账号失败!");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }
    /**
     * 重置密码
     * @param response
     * @param key
     */
    @RequestMapping("/resetPW")
    public void resetPW(HttpServletResponse response, User user,String key){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if ("qwer".equals(key)){
                try {
                    user.setPassword(MD5Utils.getMd5Str(user.getPassword()));
                    userService.updateObject(user);
                    map.put("success", true);
                } catch (Exception e) {
                    e.printStackTrace();
                    map.put("success", false);
                    map.put("info", "重置密码出错");
                }
            } else{
                map.put("success", false);
                map.put("info", "验证码错误");
            }
        } catch (Exception e) {
            map.put("success", false);
            map.put("info", "重置失败!");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }


}
