package com.upup.demo.postsystem.ng.comment.model;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author tao.li
 * @Date 2021/3/27 上午1:14
 */
@Getter
@Setter
@Builder
public class CommentWrapper {
    private Integer id;

    private Integer userId;

    private String userName;

    private Integer parentId;

    private Integer root;

    private Integer answerId;

    private String content;

    private String state;

    private Date createDatetime;

    private Date updateDatetime;
}
