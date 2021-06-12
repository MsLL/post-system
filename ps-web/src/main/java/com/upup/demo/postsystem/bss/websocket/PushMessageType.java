package com.upup.demo.postsystem.bss.websocket;

/**
 * 
 * @Date 2021/3/25 上午12:48
 */

//往clientpush的消息类型。不能乱写，不是自定义的业务消息类型。
public enum PushMessageType {
    Notify("notify"),
    Connect("connect"),
    DisConnect("disconnect");

    private PushMessageType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    private String value;
}
