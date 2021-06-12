package com.upup.demo.postsystem.ng.post.model;

import com.upup.demo.postsystem.ng.answer.model.AnswerWrapper;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @Date 2021/3/27 上午1:09 Post DTO 类。没必要专门搞个dto package，因为目前类比较少，粗一点都叫model 数据模型。
 * TODO: 改个好名字。
 */
@Getter
@Setter
@Builder
public class PostWrapper {
    private Integer id;

    private Integer userId;

    private String userName;

    private String content;

    private String state;

    private Date createDatetime;

    private Date updateDatetime;

    List<AnswerWrapper> answers;
}
