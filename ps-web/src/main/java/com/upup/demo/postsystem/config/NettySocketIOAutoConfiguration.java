package com.upup.demo.postsystem.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.upup.demo.postsystem.bss.websocket.SocketIOService;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * NettySocketConfig
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/19
 */
@Configuration
@EnableConfigurationProperties(NettySocketIOProperties.class)
public class NettySocketIOAutoConfiguration {

    @Resource
    private NettySocketIOProperties nettySocketIOProperties;

    @Resource
    private SocketIOService socketIOService;

    private static final Logger logger = LoggerFactory.getLogger(NettySocketIOAutoConfiguration.class);

    @Bean
    public SocketIOServer socketIOServer() {
        /*
         * 创建Socket，并设置监听端口
         */
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setPort(nettySocketIOProperties.getServerSocketPort());
        // 协议升级超时时间（毫秒），默认10000。HTTP握手升级为ws协议超时时间
        config.setUpgradeTimeout(10 * 1000);
        // Ping消息间隔（毫秒），默认25000。客户端向服务器发送一条心跳消息间隔
        config.setPingInterval(nettySocketIOProperties.getPingInterval());
        // Ping消息超时时间（毫秒），默认60000，这个时间间隔内没有接收到心跳消息就会发送超时事件
        config.setPingTimeout(nettySocketIOProperties.getPingTimeout());
        return new SocketIOServer(config);
    }

    /**
     *
     * NOTE-UPUP 2021/3/24 14:23 : 做(@OnConnect、@OnDisconnect、@OnEvent...)注解扫描。见com.corundumstudio.socketio.annotation package
     */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }

    //NOTE-UPUP 2021/3/24 22:58 : 返回一个runner,用来启SocketIOServer。标@Order注解 见实现的CommandLineRunner接口说明。
    @Bean
    @Order(1)
    public SocketIOServerRunner socketIOServerRunner(SocketIOServer socketServer) {
        SocketIOServerRunner runnerBean = new SocketIOServerRunner(socketServer);
        return runnerBean;
    }
}


class SocketIOServerRunner implements CommandLineRunner, DisposableBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SocketIOServer socketIOServer;

    public SocketIOServerRunner(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
    }


    @Override
    public void run(String... args) {
        logger.info("Start SocketIO server");
        socketIOServer.start();
        logger.info("Start SocketIO success");
        logger.info("点击打开首页: http://localhost:9075");
    }

    @Override
    public void destroy() throws Exception {
        socketIOServer.stop();
    }


}
