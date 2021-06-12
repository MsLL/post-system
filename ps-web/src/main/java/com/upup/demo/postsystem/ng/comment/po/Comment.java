package com.upup.demo.postsystem.ng.comment.po;

import lombok.*;

import java.util.Date;
import java.io.Serializable;

/**
 * (Comment)实体类
 *
 * @author generate by easycode
 * @since 2021-03-21 12:01:59
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    private static final long serialVersionUID = -26956733593909464L;
    
    private Integer id;
    
    private Integer userId;
    
    private Integer parentId;
    
    private Integer root;
    
    private Integer answerId;
    
    private String content;
    
    private String state;
    
    private Date createDatetime;
    
    private Date updateDatetime;
}