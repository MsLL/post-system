package com.upup.demo.postsystem.ng.post.po;

import lombok.*;

import java.util.Date;
import java.io.Serializable;

/**
 * (Post)实体类
 *
 * @author generate by easycode
 * @since 2021-03-17 23:34:49
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {
    private static final long serialVersionUID = 967394308255397300L;
    
    private Integer id;

    private Integer userId;
    
    private String content;
    
    private String state;
    
    private Date createDatetime;
    
    private Date updateDatetime;
}