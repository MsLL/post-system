package com.upup.demo.postsystem.client;

import lombok.Getter;
import lombok.Setter;
import picocli.CommandLine;

/**
 * 
 * @Date 2021/2/4 下午7:44
 */
@Getter
@Setter
public class PsClientParam {

    @CommandLine.Option(names = {"-a", "--author"}, required = true)
    String author;

    @CommandLine.Option(names = "--xxx")
    String xxx;

    @Override public String toString() {
        return "PsClientParam{" +
            "author='" + author + '\'' +
            ", xxx='" + xxx + '\'' +
            '}';
    }
}
