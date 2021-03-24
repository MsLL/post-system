package com.upup.demo.postsystem.bss.websocket;

import com.upup.demo.postsystem.bss.websocket.util.ExportEchartUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;



/**
 * @Author tao.li
 * @Date 2021/3/24 下午11:52
 */

public class PostMessageTest {



    @Test
    public void testOption() throws Exception {
        String titleStr = "对象序列化为JSON字符串";
        // 几个测试对象
        List<String> objects = Arrays.asList("FastJson", "Jackson", "Gson", "Json-lib");
        // 测试维度，输入值n
        List<String> dimensions = Arrays.asList("10000次", "100000次", "1000000次");
        // 有几个测试对象，就有几组测试数据，每组测试数据中对应几个维度的结果
        List<List<Double>> allData = new ArrayList<List<Double>>(){{
            add(Arrays.asList(2.17, 9.10, 21.70));
            add(Arrays.asList(1.94, 8.94, 19.43));
            add(Arrays.asList(4.88, 22.88, 48.89));
            add(Arrays.asList(9.11, 58.14, 108.44));
        }};
        String optionStr = ExportEchartUtil.generateEchartOption(titleStr, objects, dimensions, allData, "秒");
        // POST到接口上
        ExportEchartUtil.postEchartData(optionStr, "http://localhost:8080/websocket");
    }
}

