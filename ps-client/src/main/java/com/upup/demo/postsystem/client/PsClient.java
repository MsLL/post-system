package com.upup.demo.postsystem.client;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.KeyStroke;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import org.apache.commons.lang3.StringUtils;
import picocli.CommandLine;
import sun.security.provider.MD5;

/**
 * @Author tao.li
 * @Date 2021/2/3 下午10:57
 */


public class PsClient {
    public static void main(String[] args) {
        PsClientParam psClientParam= CommandLine.populateCommand(new PsClientParam(),args);
        System.out.println(psClientParam.toString());
    }
    //public Response doRequest(String appKey, String appSecret, int tpType, RequestBody requestBody) {
    //    String sign = getSign(appKey, appSecret, requestBody);
    //
    //    //todo
    //    //String url = "localhost://8080/openapi/ep1";
    //    //Field[] fields = requestBody.getClass().getDeclaredFields();
    //    //for (Field field : fields) {
    //    //    try {
    //    //        field.setAccessible(true);
    //    //        Object value = field.get(requestBody);
    //    //        if (value != null && StringUtils.isNotBlank(value.toString())) {
    //    //
    //    //        }
    //    //    } catch (IllegalAccessException e) {
    //    //        throw new RuntimeException(e);
    //    //    }
    //    //}
    //    //
    //    ////todo
    //    //Request request = new Request.Builder()
    //    //    .url()
    //    //    .get()
    //    //    .build();
    //}

    private String getSign(String appKey, String appSecret, RequestBody requestBody) {
        Map<String, String> map = new TreeMap<>();//treemap字段名排序model字段
        Field[] fields = requestBody.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(requestBody);
                if (value != null) {
                    map.put(field.getName(), value.toString());
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(appKey);
        map.forEach((fileName, fieldValue) -> {
            if (StringUtils.isNotEmpty(fieldValue)) {
                sb.append(fileName + "=" + fieldValue + "&");
            }
        });
        if (sb.length() > appKey.length() && StringUtils.endsWith(sb.toString(), "&")) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(appSecret);
        return new Digester(DigestAlgorithm.MD5).digestHex(sb.toString());
    }
}
