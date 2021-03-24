package com.upup.demo.postsystem.bss.websocket.util;

import com.alibaba.fastjson.JSONObject;
import com.upup.demo.postsystem.bss.websocket.model.echart.*;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportEchartUtil {

    /**
     * post to websocket hook,then websocket hook will push to ui. todo : url常量
     */
    public static void postEchartData(String optionStr, String url) throws Exception {
        final MediaType TEXT = MediaType.parse("application/text; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(TEXT, optionStr);
        Request request = new Request.Builder()
            .url(url)
            .post(body)
            .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            System.out.println(response.body().string());
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    public static String generateEchartOption(String titleStr, List<String> objects, List<String> dimensions,
        List<List<Double>> allData, String xunit) {
        JSONObject echartData = new JSONObject();
        echartData.put("title", getTitle(titleStr));
        echartData.put("tooltip", getToolTip());
        echartData.put("legend", getLegend(objects));
        echartData.put("grid", getGrid());
        echartData.put("toolbox", getToolbox());
        echartData.put("xAxis", getXAxis(xunit));
        echartData.put("yAxis", getYAxis("category", dimensions));
        echartData.put("series", getSeries(objects, allData));
        return echartData.toJSONString();
    }



    private static Title getTitle(String text) {
        return Title.builder().text(text).build();
    }

    private static Tooltip getToolTip() {
        Tooltip tooltip = Tooltip.builder()
            .trigger("axis")
            .axisPointer(
                AxisPointer.builder().type("shadow").build()
            )
            .build();
        return tooltip;
    }

    private static Legend getLegend(List<String> legends) {
        return Legend.builder().data(legends).build();
    }

    private static Grid getGrid() {
        return Grid.builder().left(100).build();
    }

    private static Toolbox getToolbox() {
        Toolbox toolbox = Toolbox.builder()
            .show(false)
            .feature(
                Feature.builder().saveAsImage(SaveAsImage.builder().type("png").build()).build()
            )
            .build();
        return toolbox;
    }

    private static XAxis getXAxis(String unit) {
        XAxis xAxis = XAxis.builder()
            .name(unit)
            .type("value")
            .build();
        return xAxis;
    }

    private static YAxis getYAxis(String type, List<String> dimensions) {

        YAxis yAxis = YAxis.builder()
            .type(type)
            .inverse(false)
            .data(dimensions)
            .axisLabel(YAxisLabel.builder().margin(20).build())
            .build();
        return yAxis;
    }

    private static List<Serie> getSeries(List<String> legends, List<List<Double>> allData) {
        List<Serie> series = new ArrayList<>();
        for (int i = 0; i < allData.size(); i++) {
            Serie serie = Serie.builder()
                .name(legends.get(i))
                .type("bar")
                .label(
                    Label.builder().normal(
                        Normal.builder().show(true).textBorderWidth(2).build()
                    ).build()
                )
                .data(allData.get(i))
                .build();
            series.add(serie);
        }
        return series;
    }
}
