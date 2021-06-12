package com.upup.demo.postsystem.ng.answer.po;

import lombok.*;
import java.io.Serializable;
import java.util.Date;

/**
 * (Answer)实体类
 *
 * @author generate by easycode
 * @since 2021-03-17 23:31:49
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer implements Serializable {
    private static final long serialVersionUID = 910168898835404852L;

    private Integer id;

    private Integer userId;

    private Integer postId;

    private String content;

    private String state;

    private Date createDatetime;

    private Date updateDatetime;
}