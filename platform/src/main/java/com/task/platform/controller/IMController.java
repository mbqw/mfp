package com.task.platform.controller;

import com.task.pojo.IMData;
import com.task.service.IMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
