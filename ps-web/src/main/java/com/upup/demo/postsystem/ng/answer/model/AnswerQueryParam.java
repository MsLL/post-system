package com.upup.demo.postsystem.ng.answer.model;

import com.upup.demo.postsystem.model.BaseQueryParam;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @Date 2021/3/20 下午10:14
 */

@Getter
@Setter
@Builder
public class AnswerQueryParam extends BaseQueryParam {
    Integer userId;
    int[] postIds;
    List<String> states;
}
