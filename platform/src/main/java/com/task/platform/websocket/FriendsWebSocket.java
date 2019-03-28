package com.task.platform.websocket;

import com.task.platform.controller.BaseController;
import com.task.pojo.User;
import com.task.pojo.UserGroup;
import com.task.service.IMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{id}")
@Component
public class FriendsWebSocket extends BaseController {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    private static transient volatile Map<String, Session> sessionMap = new ConcurrentHashMap<String, Session>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private static IMService imService;
    @Autowired
    public void setImService(IMService imService) {
        FriendsWebSocket.imService = imService;
    }

    //连接建立成功调用的方法
    @OnOpen
    public void onOpen(@PathParam("id")Integer id, Session session) {
        try {
            this.session = session;
            sessionMap.put(id+"",session);
            addOnlineCount();           //在线数加1
            System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
            //通知在线的所有好友已上线
            List<UserGroup> list = imService.getFriendsById(id+"");
            Map params = new HashMap();
            params.put(id,"online");
            //发送回去的数据
            Map resMap = new HashMap();
            resMap.put("emit","isOnline");
            resMap.put("data",params);
            for (UserGroup userGroup : list) {
                for (User user : userGroup.getList()) {
                    Session toSession = sessionMap.get(user.getId()+"");
                    if (toSession != null){
                        toSession.getBasicRemote().sendText(objectToJson(resMap));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //连接关闭调用的方法
    @OnClose
    public void onClose() {
        try {
            String id = this.session.getPathParameters().get("id");
            sessionMap.remove(id);
            subOnlineCount();           //在线数减1
            System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
            //通知在线的所有好友已下线
            List<UserGroup> list = imService.getFriendsById(id+"");
            Map params = new HashMap();
            params.put(id,"offline");
            //发送回去的数据
            Map resMap = new HashMap();
            resMap.put("emit","isOnline");
            resMap.put("data",params);
            for (UserGroup userGroup : list) {
                for (User user : userGroup.getList()) {
                    Session toSession = sessionMap.get(user.getId()+"");
                    if (toSession != null){
                        toSession.getBasicRemote().sendText(objectToJson(resMap));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //收到客户端消息后调用的方法
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            Map<String,Map> map = (Map<String,Map>) jsonToObject(message,Map.class);
            String type =(String) map.get("type").get("type");
            if ("friend".equals(type)){
                friend(session, map);
            }else if("group".equals(type)){
                group(session, map);
            }else if("addFriend".equals(type)){
                addFriend(session, map);
            }else if("addGroup".equals(type)){
                addGroup(session, map);
            }else if("isOnline".equals(type)){
                isOnline(session, map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //得到所有好友的在线状态
    private void isOnline(Session session, Map<String, Map> map) throws IOException {
        Map data = map.get("data");
        List<UserGroup> list = imService.getFriendsById(data.get("id"));
        Map params = new HashMap();
        for (UserGroup userGroup : list) {
            for (User user : userGroup.getList()) {
                Session toSession = sessionMap.get(user.getId()+"");
                if (toSession != null){
                    params.put(user.getId(),"online");

                }else{
                    params.put(user.getId(),"offline");
                }
            }
        }
        //发送回去的数据
        Map resMap = new HashMap();
        resMap.put("emit","isOnline");
        resMap.put("data",params);
        session.getBasicRemote().sendText(objectToJson(resMap));
    }
    //添加群组
    private void addGroup(Session session, Map<String, Map> map) throws IOException {
        Map data = map.get("data");
        Map params = new HashMap();
        params.put("system",true);
        params.put("id",data.get("groupId"));
        params.put("type","group");
        params.put("content",data.get("from")+"     邀请 "+data.get("usernames")+" 加入群聊");
        List<Integer> list = imService.getGroupMembersById(data.get("groupId"));
        for (Integer integer : list) {
            Session toSession = sessionMap.get(integer+"");
            if (toSession != null){
                //发送回去的数据
                Map resMap = new HashMap();
                resMap.put("emit","addGroup");
                resMap.put("data",params);
                toSession.getBasicRemote().sendText(objectToJson(resMap));
            }
        }

    }
    //添加好友
    private void addFriend(Session session, Map<String, Map> map) throws IOException {
        Map data = map.get("data");
        data.put("type","1");
        imService.addMsgbox(data);
        Object descUid = data.get("descUid");
        Session toSession = sessionMap.get(descUid+"");
        if (toSession != null){
            Integer count = imService.disAgreeMsgbox(descUid);
            //发送回去的数据
            Map resMap = new HashMap();
            resMap.put("emit","msgbox");
            resMap.put("count",count);
            toSession.getBasicRemote().sendText(objectToJson(resMap));
        }
    }
    //群组信息
    private void group(Session session, Map<String, Map> map) throws IOException {
        Map mine = map.get("mine");
        Map to = map.get("to");
        Map chat = new HashMap();
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        chat.put("srcUid",mine.get("id"));
        chat.put("descGid",to.get("id"));
        chat.put("content",mine.get("content"));
        chat.put("createTime",date);
        chat.put("status",1);
        imService.addChat(chat);
        mine.put("fromid",mine.get("id"));
        mine.put("id",to.get("id"));
        mine.put("mine",false);
        mine.put("type",to.get("type"));
        mine.put("timestamp",date);
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
    //好友信息
    private void friend(Session session, Map<String, Map> map) throws IOException {

        Map mine = map.get("mine");
        Map to = map.get("to");
        Map data = new HashMap();
        Map chat = new HashMap();
        Object id = to.get("id");
        Session toSession = sessionMap.get(id+"");
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        chat.put("srcUid",mine.get("id"));
        chat.put("descUid",id);
        chat.put("content",mine.get("content"));
        chat.put("createTime",date);
        if (toSession == null){
            toSession = session;
            data.put("system",true);
            data.put("id",id);
            data.put("type",to.get("type"));
            data.put("content","对方不在线,将离线发送");
            chat.put("status",0);
        }else{
            data = mine;
            data.put("mine",false);
            data.put("type",to.get("type"));
            data.put("fromid",mine.get("id"));
            data.put("timestamp",date);
            chat.put("status",1);
        }
        imService.addChat(chat);
        Map resMap = new HashMap();
        resMap.put("emit","friend");
        resMap.put("data",data);
        toSession.getBasicRemote().sendText(objectToJson(resMap));
    }

    //发生错误时调用
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