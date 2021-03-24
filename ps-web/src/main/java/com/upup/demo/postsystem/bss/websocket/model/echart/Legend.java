package com.upup.demo.postsystem.bss.websocket.model.echart;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Builder
@Getter
@Setter
public class Legend {
    private List<String> data;

}
