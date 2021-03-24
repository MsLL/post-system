package com.upup.demo.postsystem.bss.websocket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.upup.demo.postsystem.config.SocketIOProperties;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocketIOService {

    private static final Logger logger = LoggerFactory.getLogger(SocketIOService.class);
    /**
     * 保存最后一个连接上来的sessionID
     */
    private String sessionId;

    @Autowired
    private SocketIOProperties socketIOProperties;

    @Autowired
    private SocketIOServer server;

    public synchronized void updateSessionId(String sid) {
        sessionId = sid;
    }

    /**
     * 服务器主动推送消息
     *
     * @param messageType 消息类型
     * @param data        echarts图表数据
     */
    public void pushMsssage(PushMessageType messageType, String data) {
        SocketIOClient targetClient = this.server.getClient(UUID.fromString(sessionId));
        if (targetClient == null) {
            logger.error("sessionId=" + sessionId + "在server中获取不到client");
        } else {
            logger.info("push {} message to client with session {}", messageType.value(), targetClient.getSessionId().toString());
            targetClient.sendEvent(messageType.value(), data);
        }
    }
}
