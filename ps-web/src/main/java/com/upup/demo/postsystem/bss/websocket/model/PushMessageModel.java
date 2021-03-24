package com.upup.demo.postsystem.bss.websocket.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 * @Author tao.li
 * @Date 2021/3/24 下午11:15
 * 服务端主动给客户端推送消息的消息数据模型
 */

@Getter
@Setter
@Builder
public class PushMessageModel {
    String type;
    String content;
}
