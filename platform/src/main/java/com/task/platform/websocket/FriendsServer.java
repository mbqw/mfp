package com.task.platform.websocket;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/chat")
public class FriendsServer {
    //在线连接数
    private static int onlineCount = 0;
    //每个客户端对应的WebSocket对象。
    private static CopyOnWriteArraySet<FriendsServer> webSocketSet = new CopyOnWriteArraySet<FriendsServer>();
    //与客户端的连接会话
    private Session session;

}
