package com.upup.demo.postsystem.bss.websocket.model.echart;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Builder
public class Tooltip {
    private String trigger;
    private AxisPointer axisPointer;
}
