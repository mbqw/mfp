package com.task.platform.websocket;

import com.task.platform.controller.BaseController;
import com.task.service.IMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{srcId}")
@Component
public class FriendsWebSocket extends BaseController {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    private static transient volatile Map<String, Session> sessionMap = new ConcurrentHashMap<String, Session>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    @Autowired
    private static IMService imService;
    @Autowired
    public void setChatService(IMService imService) {
        FriendsWebSocket.imService = imService;
    }

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(@PathParam("srcId")Integer id, Session session) {
        this.session = session;
        sessionMap.put(id+"",session);
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        sessionMap.remove(this);
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            Map<String,Map> map = (Map<String,Map>) jsonToObject(message,Map.class);
            String type =(String) map.get("type").get("type");
            if ("friend".equals(type)){
                friend(session, map);
            }else if("group".equals(type)){
                group(session, map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 群组信息
     * @param session
     * @param map
     * @throws IOException
     */
    private void group(Session session, Map<String, Map> map) throws IOException {
        Map mine = map.get("mine");
        Map to = map.get("to");
        mine.put("fromid",mine.get("id"));
        mine.put("id",to.get("id"));
        mine.put("mine",false);
        mine.put("type",to.get("type"));
        mine.put("timestamp",new Date());
        //发送回去的数据
        Map resMap = new HashMap();
        resMap.put("emit","group");
        resMap.put("data",mine);
        List<Integer> list = imService.getGroupMembersById(to.get("id"));
        list.remove(list.indexOf(mine.get("fromid")));
        for (Integer integer : list) {
            Session toSession = sessionMap.get(integer+"");
            if (toSession != null){
                toSession.getBasicRemote().sendText(objectToJson(resMap));
            }
        }
    }
    /**
     * 好友信息
     * @param session
     * @param map
     * @throws IOException
     */
    private void friend(Session session, Map<String, Map> map) throws IOException {
        Object id = map.get("to").get("id");
        Map to =null;
        Session toSession = sessionMap.get(id+"");
        if (toSession == null){
            toSession = session;
            to = new HashMap();
            to.put("system",true);
            to.put("id",id);
            to.put("type",map.get("to").get("type"));
            to.put("content","对方不在线");
        }else{
            to = map.get("mine");
            to.put("mine",false);
            to.put("type",map.get("to").get("type"));
            to.put("fromid",to.get("id"));
            to.put("timestamp",new Date());
        }
        Map resMap = new HashMap();
        resMap.put("emit","friend");
        resMap.put("data",to);
        toSession.getBasicRemote().sendText(objectToJson(resMap));
    }

    /**
     * 发生错误时调用
     */

     @OnError
     public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
     }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        FriendsWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        FriendsWebSocket.onlineCount--;
    }
}