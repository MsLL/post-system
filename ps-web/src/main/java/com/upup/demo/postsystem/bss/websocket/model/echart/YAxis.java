package com.upup.demo.postsystem.bss.websocket.model.echart;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class YAxis {
    private String type;
    private boolean inverse;
    private List<String> data;
    private YAxisLabel axisLabel;
}
