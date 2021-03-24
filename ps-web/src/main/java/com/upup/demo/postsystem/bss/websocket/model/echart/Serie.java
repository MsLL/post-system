package com.upup.demo.postsystem.bss.websocket.model.echart;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Serie {
    private String name;
    private String type;
    private List<Double> data;
    private Label label;
}
