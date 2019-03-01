package com.task.platform.controller;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.task.pojo.DataGridModel;
import com.task.pojo.DynamicMsg;
import com.task.pojo.User;
import com.task.service.DynamicMsgService;
import com.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/platform")
public class PlatformController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private DynamicMsgService dynamicMsgService;

    //跳转到主页
    @RequestMapping("/index/{id}")
    public ModelAndView index(ModelMap modelMap, @PathVariable Integer id){
        modelMap.addAttribute("user",userService.getObjectById(id));
        return new ModelAndView("platform",modelMap);
    }
    //弹出到详情页
    @RequestMapping("/detail/{id}")
    public ModelAndView detail(ModelMap modelMap, @PathVariable Integer id){
        modelMap.addAttribute("id",id);
        return new ModelAndView("/user/detail",modelMap);
    }
    //获取数据
    @RequestMapping("/getData")
    public void getData(DataGridModel dataGridModel, HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = super.getOut(response);
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> params = dataGridModel.getQueryParams();
        try {
            PageBounds pageBounds = new PageBounds(dataGridModel.getPage(),10, Order.formString(dataGridModel.getSort() + "." + dataGridModel.getOrder()));
            PageList<DynamicMsg> pageList = dynamicMsgService.findByObjectPageList(params, pageBounds);
            if (pageList != null) {
                map.put("total", pageList.getPaginator().getTotalCount());
                map.put("rows", pageList);
                if(pageList.size() == 0){
                    map.put("success",false);
                    map.put("info","已经到底了");
                } else {
                    map.put("success",true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("info", "获取数据失败!");
        }
        out.print(super.objectToJson(map));
    }
}
