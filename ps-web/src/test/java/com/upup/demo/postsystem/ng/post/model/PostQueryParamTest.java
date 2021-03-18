package com.upup.demo.postsystem.ng.post.model;

import com.upup.demo.postsystem.ng.post.entity.Post;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author tao.li
 * @Date 2021/3/18 下午8:47
 */
class PostQueryParamTest {
    @Test
    public void test0() {
        PostQueryParam queryParam = new PostQueryParam();
        queryParam.setLimit(9999);
        assertEquals(queryParam.getLimit(), 1000);

    }
}