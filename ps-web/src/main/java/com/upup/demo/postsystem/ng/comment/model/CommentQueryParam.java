package com.upup.demo.postsystem.ng.comment.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.upup.demo.postsystem.model.BaseQueryParam;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author tao.li
 * @Date 2021/3/21 下午12:46
 */
@Getter
@Setter
public class CommentQueryParam extends BaseQueryParam {
    Integer userId;
    Integer answerId;
    Integer parentId;
    Integer rootId;
    List<String> states;
}
