package com.upup.demo.postsystem.ng.comment.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.upup.demo.postsystem.model.BaseQueryParam;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @Date 2021/3/21 下午12:46
 */
@Getter
@Setter
@Builder
public class CommentQueryParam extends BaseQueryParam {
    Integer userId;
    int[] answerIds;
    Integer parentId;
    Integer rootId;
    List<String> states;
}
