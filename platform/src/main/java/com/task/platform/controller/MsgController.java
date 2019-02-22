package com.task.platform.controller;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.task.pojo.DataGridModel;
import com.task.pojo.DynamicMsg;
import com.task.pojo.Star;
import com.task.service.DynamicMsgService;
import com.task.service.StarService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
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
    //静态资源路径
    @Value(value = "${upload_images_path}")
    private String upload_images_path;
    @Autowired
    private StarService starService;
    //跳转到主页
    @RequestMapping("/toAdd/{id}")
    public ModelAndView toAdd(ModelMap modelMap, @PathVariable Integer id){
        modelMap.addAttribute("id",id);
        //User objectById = userService.getObjectById("");
        return new ModelAndView("/msg/add",modelMap);
    }
    //动态详情
    @RequestMapping("/toList/{id}")
    public ModelAndView detail(ModelMap modelMap, @PathVariable Integer id){
        modelMap.addAttribute("id",id);
        //User objectById = userService.getObjectById("");
        return new ModelAndView("/msg/list",modelMap);
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
            msg.setContent(msg.getContent().replaceAll("(http(s?)://)[a-zA-Z0-9.:]*",""));
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
    //获取发布的内容
    @RequestMapping("/getContent")
    public void getContent(HttpServletRequest request, HttpServletResponse response, Integer id){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            DynamicMsg dynamicMsg = dynamicMsgService.getObjectById(id);
            map.put("success", true);
            map.put("info", dynamicMsg.getContent());
        } catch (Exception e) {
            map.put("success", false);
            map.put("info", "获取失败");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }
    //点赞或踩或收藏
    @RequestMapping("/update")
    public void islike(HttpServletRequest request, HttpServletResponse response, DynamicMsg msg){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            dynamicMsgService.updateObject(msg);
            map.put("success", true);
            map.put("info", "操作成功");
        } catch (Exception e) {
            map.put("success", false);
            map.put("info", "操作失败");
            e.printStackTrace();
        }
        out.print(super.objectToJson(map));
    }
    //获取已收藏分页数据
    @RequestMapping("/starList")
    public void starList(DataGridModel dataGridModel, HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> params = dataGridModel.getQueryParams();
        try {
            PageBounds pageBounds = new PageBounds(dataGridModel.getPage(),dataGridModel.getRows(), Order.formString(dataGridModel.getSort() + "." + dataGridModel.getOrder()));
            PageList<DynamicMsg> pageList = dynamicMsgService.findStarMsgPageList(params, pageBounds);
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
}
