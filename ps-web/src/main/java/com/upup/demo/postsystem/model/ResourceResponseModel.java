package com.upup.demo.postsystem.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author tao.li
 * @Date 2021/1/26 下午11:25
 */
@Getter
@Setter
@Builder
public class ResourceResponseModel {
    private int code;
    private Object data;
}
