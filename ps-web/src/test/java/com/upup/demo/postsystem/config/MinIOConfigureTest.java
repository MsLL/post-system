package com.upup.demo.postsystem.config;

import io.minio.MinioClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author tao.li
 * @Date 2021/2/9 22:00
 */
@SpringBootTest
class MinIOConfigureTest {
    @Autowired
    MinioClient minioClient;

    @Test
    public void test0() throws Exception {
        minioClient.listBuckets().stream().forEach(bucket -> {
            System.out.println(bucket.name());
        });
    }
}