package com.upup.demo.postsystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @Date 2021/2/9 21:48
 */
@ConfigurationProperties("minio")
public class MinIOProperties {
    private String host = "localhost";

    private int port = 9000;

    private String username = "root";

    private String password = "12345678";

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
