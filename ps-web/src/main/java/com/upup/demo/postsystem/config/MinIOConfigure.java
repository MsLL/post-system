package com.upup.demo.postsystem.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author tao.li
 * @Date 2021/2/9 21:47
 */
@Configuration
@EnableConfigurationProperties(MinIOProperties.class)
public class MinIOConfigure {

    @Autowired
    MinIOProperties minIOProperties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minIOProperties.getHost(), minIOProperties.getPort(), false)
                .credentials(minIOProperties.getUsername(), minIOProperties.getPassword())
                .build();
    }
}
