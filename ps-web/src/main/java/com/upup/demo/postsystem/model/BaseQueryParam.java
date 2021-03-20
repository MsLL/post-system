package com.upup.demo.postsystem.model;

import com.upup.demo.postsystem.dictionary.Constants;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author tao.li
 * @Date 2021/3/20 下午10:06
 */

@Getter
@Setter
public class BaseQueryParam {

    /**
     * default limit 1000。if null means no limit
     */
    Integer limit = Constants.MAX_QUERY_BATCH;


    /**
     * if limit > Constants.MAX_QUERY_BATCH(1000), will be set to Constants.MAX_QUERY_BATCH(1000)。如果子类想超过这个系统约束值，自行重写该方法。
     */
    public void setLimit(int limit) {
        limit = Math.min(limit, Constants.MAX_QUERY_BATCH);
        this.limit = limit;
    }

    /**
     * if null,means no offset
     */
    Integer offset;


    /**
     * here createDate means >=
     */
    Date createDate;

    /**
     * here updateDate means >=
     */
    Date updateDate;
}
