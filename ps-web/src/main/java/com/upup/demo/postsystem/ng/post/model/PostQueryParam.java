package com.upup.demo.postsystem.ng.post.model;

import com.upup.demo.postsystem.dictionary.Constants;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author tao.li
 * @Date 2021/3/18 下午7:40 todo ： 不知道应该放在哪，先放在这里吧
 */
//NOTE-UPUP 2021/3/18 下午7:33 : 针对可能出现的花式查询,封装为一个查询参数对象。当需要新增/修改查询逻辑时，基本应该只需要改该类体和Mapper.xml文件。

@Getter
@Setter
public class PostQueryParam {

    List<String> states;

    /**
     * if null,means no offset
     */
    Integer offset;

    /**
     * default limit 1000。if null means no limit
     */
    Integer limit = Constants.MAX_QUERY_BATCH;

    public void setLimit(int limit) {
        limit = Math.min(limit, Constants.MAX_QUERY_BATCH);
        this.limit = limit;
    }
}
