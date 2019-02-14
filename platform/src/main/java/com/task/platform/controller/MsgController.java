package com.task.platform.controller;

import com.task.pojo.DynamicMsg;
import com.task.service.DynamicMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/msg")
public class MsgController extends BaseController{
    @Autowired
    private DynamicMsgService dynamicMsgService;

    @Value(value = "${upload_images_path}")
    private String upload_images_path;
    //跳转到主页
    @RequestMapping("/toAdd/{id}")
    public ModelAndView toAdd(ModelMap modelMap, @PathVariable Integer id){
        modelMap.addAttribute("id",id);
        //User objectById = userService.getObjectById("");
        return new ModelAndView("/add",modelMap);
    }
    //上传文件
    @RequestMapping("/uploadImgs/{id}")
    public void uploadImgs(HttpServletResponse response, HttpServletRequest request, @PathVariable Integer id, MultipartFile file){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (!file.isEmpty()){
                File dir = new File(upload_images_path+"msg\\"+id+"\\");
                if (!dir.exists()){
                    dir.mkdir();
                }
                String prefix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                String uuid = UUID.randomUUID().toString().replace("-","");
                File dFile = new File(dir,uuid+prefix);
                file.transferTo(dFile);
                map.put("info","\\msg\\"+id+"\\"+uuid+prefix);
                map.put("success", true);
            }else{
                map.put("success", false);
            }

        } catch (Exception e) {
            map.put("success", false);
            map.put("info", "上传图片出错");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }
    //发布
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, DynamicMsg msg){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            dynamicMsgService.addObject(msg);
            map.put("success", true);
        } catch (Exception e) {
            String[] img = msg.getImg();
            for (String s : img) {
                File file = new File(upload_images_path+s);
                if (file.exists()){
                    file.delete();
                }
            }
            map.put("success", false);
            map.put("info", "发布动态失败");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }

}
