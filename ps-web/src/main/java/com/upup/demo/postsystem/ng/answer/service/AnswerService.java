package com.upup.demo.postsystem.ng.answer.service;

import com.upup.demo.postsystem.ng.answer.model.AnswerQueryParam;
import com.upup.demo.postsystem.ng.answer.model.AnswerWrapper;
import com.upup.demo.postsystem.ng.answer.po.Answer;
import java.util.List;

/**
 * (Answer)表服务接口
 *
 * @author generate by easycode
 * @since 2021-01-31 23:44:31
 */
public interface AnswerService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AnswerWrapper findById(Integer id);

    /**
     * 查询多条数据
     *
     *
     * @param queryParam@return 对象列表
     */
    List<AnswerWrapper> list(AnswerQueryParam queryParam);

    /**
     * 新增数据
     *
     * @param answer 实例对象
     * @return 实例对象
     */
    Answer insert(Answer answer);

    /**
     * 修改数据
     *
     * @param answer 实例对象
     * @return 实例对象
     */
    Answer update(Answer answer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}