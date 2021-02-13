package com.upup.demo.postsystem.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientProperties;
import org.springframework.boot.autoconfigure.elasticsearch.RestClientBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Author tao.li
 * @Date 2021/2/13 20:16
 * imitate org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration
 * spring-boot autoconfigure 包已经有了es相关的自动配置，more看类上面conditional注解。直接用，这里先留白。
 */
@Configuration
public class ElasticsearchRestClientAutoConfiguration {
}
