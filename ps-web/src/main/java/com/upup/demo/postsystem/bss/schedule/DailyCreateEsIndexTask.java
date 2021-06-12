package com.upup.demo.postsystem.bss.schedule;

import com.upup.demo.postsystem.dictionary.Constants;
import com.upup.demo.postsystem.util.SoutExceptionUtil;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 
 * @Date 2021/2/13 22:06
 * es没有create index if not exist api,所以搞个job，预创建索引。每天凌晨跑一次，保证当天及后三天的index都已经创建。
 */
@Component
public class DailyCreateEsIndexTask {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Scheduled(cron = "0 0 0 * * ?")
    public void createLogIndex() {
        DateTime dateTime = new DateTime();
        System.out.println("start DailyCreateEsIndexTask at: " + dateTime.toString("yyyy_MM_dd HH:mm:ss,sss"));
        for (int i = 0; i <= 2; i++) {
            try {
                createIndexIfNotExist(Constants.ES_LOG_PREFIX + dateTime.plusDays(i).toString("yyyy_MM_dd"));
            } catch (Exception e) {
                SoutExceptionUtil.soutException(e);
            }
        }
        System.out.println("finish DailyCreateEsIndexTask at: " + new DateTime().toString("yyyy_MM_dd HH:mm:ss,sss"));
    }

    /**
     * @param indexName example: post-system-2021_02_13
     */
    public void createIndexIfNotExist(String indexName) throws IOException {
        GetIndexRequest request = new GetIndexRequest(indexName);
        if (!restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT)) {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
            createIndexRequest.settings(Settings.builder()
                    .put("index.number_of_shards", 3)
                    .put("index.number_of_replicas", 1)
            );
            restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        }
    }
}
