package com.upup.demo.postsystem.ng.answer.model;

import com.upup.demo.postsystem.ng.comment.model.CommentWrapper;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author tao.li
 * @Date 2021/3/27 上午1:12
 */
@Getter
@Setter
@Builder
public class AnswerWrapper {
    private Integer id;

    private Integer userId;
    
    private String userName;

    private Integer postId;

    private String content;

    private String state;

    private Date createDatetime;

    private Date updateDatetime;

    private List<CommentWrapper> comments;
}
