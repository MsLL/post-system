package com.upup.demo.postsystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.upup.demo.postsystem.config.ElasticsearchRestClientAutoConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientProperties;
import org.yaml.snakeyaml.Yaml;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * 
 * @Date 2021/2/13 23:37
 */
public class SnakeymlTest {

    /**
     * 报错，不能map部分section到object
     */
    @Test
    public void test0() {
        Yaml yaml = new Yaml();
        ElasticsearchRestClientProperties elasticsearchRestClientProperties = yaml.loadAs(this.getClass().getClassLoader().getResourceAsStream("application-prod.yml"), ElasticsearchRestClientProperties.class);
        System.out.println(new Gson().toJson(elasticsearchRestClientProperties));
    }


    /**
     * yaml中的部分转化为数据对象
     */
    @Test
    public void test2() {

        //step1: yaml to json
        Yaml yaml = new Yaml();
        Object obj = yaml.load(this.getClass().getClassLoader().getResourceAsStream("application-prod.yml"));

        JsonElement jsonElement = new Gson().toJsonTree(obj).getAsJsonObject().getAsJsonObject("spring").getAsJsonObject("elasticsearch").getAsJsonObject("rest");

        //step2:json to dto obj
        ElasticsearchRestClientProperties elasticsearchRestClientProperties = new Gson().fromJson(jsonElement, ElasticsearchRestClientProperties.class);

        //
        System.out.println(new Gson().toJson(elasticsearchRestClientProperties));
    }
}
