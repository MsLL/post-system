package com.upup.demo.postsystem.ng.answer;

import com.upup.demo.postsystem.model.BaseQueryParam;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author tao.li
 * @Date 2021/3/20 下午10:14
 */

@Getter
@Setter
public class AnswerQueryParam extends BaseQueryParam {
    Integer userId;
    Integer postId;
    List<String> states;
}
