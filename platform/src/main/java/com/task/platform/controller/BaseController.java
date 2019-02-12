package com.task.platform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class BaseController {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    /**
     * 得到out对象
     *
     * @param response
     * @return
     */
    public PrintWriter getOut(HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setContentType("text/html; charset=UTF-8");
            out = response.getWriter();
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return out;
    }
    /**
     * 对象转成json
     *
     * @param object
     * @return
     */
    public String objectToJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(object);
            return json;
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return null;
    }
    /**
     * json转对象
     *
     * @param c
     * @return
     */
    public Object jsonToObject(String json, Class c) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Object obj = objectMapper.readValue(json, c);
            return obj;
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return null;
    }
}
