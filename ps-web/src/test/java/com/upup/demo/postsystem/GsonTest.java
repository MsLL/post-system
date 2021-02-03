package com.upup.demo.postsystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.upup.demo.postsystem.bss.user.model.UserModel;
import org.junit.jupiter.api.Test;

/**
 * @Author tao.li
 * @Date 2021/1/30 下午7:15
 */
public class GsonTest {

    @Test
    public void test0(){
        UserModel userModel=UserModel.builder()
            .id(1)
            .salt("xxx")
            .name("A1")
            .build();

        System.out.println(userModel.toString());
        System.out.println(new Gson().toJson(userModel));

//https://stackoverflow.com/questions/3923759/gson-ignoring-map-entries-with-value-null
        Gson gson = new GsonBuilder().serializeNulls().create();
        System.out.println(gson.toJson(userModel));
    }
}

