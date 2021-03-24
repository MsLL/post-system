package com.upup.demo.postsystem.bss.resource;

import com.google.gson.Gson;
import com.upup.demo.postsystem.bss.websocket.PushMessageType;
import com.upup.demo.postsystem.bss.websocket.SocketIOService;
import com.upup.demo.postsystem.bss.websocket.model.PushMessageModel;
import com.upup.demo.postsystem.model.ResourceResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author tao.li
 * @Date 2021/3/24 下午11:03 todo: 1. hook。
 */
@Controller
@ResponseBody
@RequestMapping("/websocket")
public class WebSocketResource {

    @Autowired
    SocketIOService socketIOService;

    @PostMapping
    public ResourceResponseModel pushMessage(@RequestBody String data) {
        PushMessageModel pushMessageModel=PushMessageModel.builder()
            .type("show-as-echart")
            .content(data)
            .build();
        socketIOService.pushMsssage(PushMessageType.Notify, new Gson().toJson(pushMessageModel));
        return ResourceResponseModel.builder().code(200).build();
    }
}
