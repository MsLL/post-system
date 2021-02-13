package com.upup.demo.postsystem.config;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author tao.li
 * @Date 2021/2/13 20:36
 */
@SpringBootTest
class ElasticsearchRestClientAutoConfigurationTest {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Test
    public void testIndexExist() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest("post-system-2021_02_13", ".kibana_1");
        boolean exist = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        Assertions.assertFalse(exist);

        exist = restHighLevelClient.indices().exists(new GetIndexRequest(".kibana_1"), RequestOptions.DEFAULT);
        Assertions.assertTrue(exist);
    }
}
