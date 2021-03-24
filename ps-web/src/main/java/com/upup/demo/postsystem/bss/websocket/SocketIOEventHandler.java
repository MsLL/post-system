package com.upup.demo.postsystem.bss.websocket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息事件处理器
 * //NOTE-UPUP 2021/3/24 23:01 : NettySocketIOAutoConfiguration#SpringAnnotationScanner处理这里的注解
 */
@Component
public class SocketIOEventHandler {
    @Autowired
    private SocketIOService socketIOService;

    private static final Logger logger = LoggerFactory.getLogger(SocketIOEventHandler.class);


    /**
     * 添加connect事件，当客户端发起连接时调用
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        if (client != null) {
            final String sessionId = client.getSessionId().toString();
            logger.info("连接成功, sessionId=" + sessionId);
            // 赶紧保存这个sessionID呀
            socketIOService.updateSessionId(sessionId);
        } else {
            logger.error("客户端为空");
        }
    }

    /**
     * 添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        logger.info("客户端断开连接, sessionId=" + client.getSessionId().toString());
        client.disconnect();
    }

}
