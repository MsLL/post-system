package com.upup.demo.postsystem.bss.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.upup.demo.postsystem.dictionary.Constants;
import com.upup.demo.postsystem.enums.Env;
import com.upup.demo.postsystem.util.SoutExceptionUtil;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.joda.time.DateTime;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientProperties;
import org.yaml.snakeyaml.Yaml;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @Date 2021/2/9 23:00
 */
public class EsAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    private Gson gson = new GsonBuilder().serializeNulls().create();
    private BulkProcessor bulkProcessor;

    @Override
    protected void append(ILoggingEvent eventObject) {
        LogModel logModel = new LogModel(eventObject.getFormattedMessage(), eventObject.getLevel().levelStr, eventObject.getLoggerName(), eventObject.getThreadName(), eventObject.getTimeStamp());
        String index = Constants.ES_LOG_PREFIX + new DateTime().toString("yyyy_MM_dd");
        IndexRequest indexRequest = new IndexRequest(index);
        indexRequest.id("post-system-" + UUID.randomUUID().toString());
        indexRequest.source(gson.toJson(logModel), XContentType.JSON);
        this.bulkProcessor.add(indexRequest);
    }

    //初始化es客户端
    @Override
    public void start() {
        this.bulkProcessor = getBulkProcessor();
        super.start();
    }

    @Override
    public void stop() {
        this.bulkProcessor.flush();
        this.bulkProcessor.close();
        super.stop();
    }

    private Env getEnv() {
        Yaml yaml = new Yaml();
        Object obj = yaml.load(this.getClass().getClassLoader().getResourceAsStream("application.yml"));
        String env = new Gson().toJsonTree(obj).getAsJsonObject().getAsJsonObject("spring").getAsJsonObject("profiles").get("active").getAsString();

        for (Env e : Env.values()) {
            if (e.name().equalsIgnoreCase(env)) {
                return e;
            }
        }
        throw new RuntimeException("not found effective active profiles in application.yml");
    }

    private ElasticsearchRestClientProperties loadElasticsearchRestClientProperties() {
        Env env = getEnv();
        String properties = "application-" + env.name().toLowerCase() + ".yml";

        //step1: yaml to json
        Yaml yaml = new Yaml();
        Object obj = yaml.load(this.getClass().getClassLoader().getResourceAsStream(properties));

        ElasticsearchRestClientProperties elasticsearchRestClientProperties = new ElasticsearchRestClientProperties();

        try {

            JsonElement jsonElement = new Gson().toJsonTree(obj).getAsJsonObject().getAsJsonObject("spring").getAsJsonObject("elasticsearch").getAsJsonObject("rest");

            //step2:json to dto obj
            elasticsearchRestClientProperties = new Gson().fromJson(jsonElement, ElasticsearchRestClientProperties.class);
        } catch (Exception e) {
            SoutExceptionUtil.soutException(e);

        }

        return elasticsearchRestClientProperties;
    }

    private RestHighLevelClient getRestHighLevelClient() {
        ElasticsearchRestClientProperties properties = loadElasticsearchRestClientProperties();

        RestClientBuilder builder = RestClient.builder(properties.getUris().stream().map(uri -> HttpHost.create(uri)).toArray(HttpHost[]::new));

        builder.setHttpClientConfigCallback(httpAsyncClientBuilder -> {
            if (properties.getUsername() != null) {
                CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(properties.getUsername(), properties.getPassword()));
                httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }
            return httpAsyncClientBuilder;
        });

        return new RestHighLevelClient(builder);
    }

    private BulkProcessor getBulkProcessor() {
        RestHighLevelClient restHighLevelClient = getRestHighLevelClient();

        BulkProcessor.Listener listener = new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long l, BulkRequest bulkRequest) {
                System.out.println("do bulk ,size : " + bulkRequest.requests().size());
            }

            @Override
            public void afterBulk(long l, BulkRequest bulkRequest, BulkResponse bulkResponse) {
            }

            @Override
            public void afterBulk(long l, BulkRequest bulkRequest, Throwable throwable) {
                SoutExceptionUtil.soutException(throwable);
            }
        };

        BulkProcessor bulkProcessor = BulkProcessor.builder(
                (request, bulkListener) -> {
                    restHighLevelClient.bulkAsync(request, RequestOptions.DEFAULT, bulkListener);
                }, listener)
                .setBulkActions(100)
                .setBulkSize(new ByteSizeValue(1, ByteSizeUnit.MB))
                .setFlushInterval(new TimeValue(60, TimeUnit.SECONDS))
                .build();
        return bulkProcessor;
    }
}

class LogModel {
    String logMessage;
    String logLevel;
    String loggerName;
    String logThreadName;
    long timeStampt;

    public LogModel(String logMessage, String logLevel, String loggerName, String logThreadName, long timeStampt) {
        this.logMessage = logMessage;
        this.logLevel = logLevel;
        this.loggerName = loggerName;
        this.logThreadName = logThreadName;
        this.timeStampt = timeStampt;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLogThreadName() {
        return logThreadName;
    }

    public void setLogThreadName(String logThreadName) {
        this.logThreadName = logThreadName;
    }

    public long getTimeStampt() {
        return timeStampt;
    }

    public void setTimeStampt(long timeStampt) {
        this.timeStampt = timeStampt;
    }
}
