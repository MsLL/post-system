package com.upup.demo.postsystem.config;

import io.minio.MinioClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * @Date 2021/2/9 22:00
 */
@SpringBootTest
class MinIOConfigurationTest {
    @Autowired
    MinioClient minioClient;

    @Test
    public void test0() throws Exception {
        minioClient.listBuckets().stream().forEach(bucket -> {
            System.out.println(bucket.name());
        });
    }
}
